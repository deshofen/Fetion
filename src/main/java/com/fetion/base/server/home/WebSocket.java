package com.fetion.base.server.home;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fetion.base.util.AesUtil;
import com.fetion.base.util.RsaUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.fetion.base.bean.WebSocketMsg;
import com.fetion.base.entity.common.Account;
import com.fetion.base.entity.common.AccountGroupMember;
import com.fetion.base.entity.common.Friend;
import com.fetion.base.entity.common.MsgContent;
import com.fetion.base.entity.common.MsgLog;
import com.fetion.base.service.common.AccountGroupMemberService;
import com.fetion.base.service.common.AccountService;
import com.fetion.base.service.common.FriendService;
import com.fetion.base.service.common.MsgContentService;
import com.fetion.base.service.common.MsgLogService;

@Service
@ServerEndpoint("/webSocket/{userid}/{publicKey}")
public class WebSocket {

	public static Map<Long, WebSocket> clients = new ConcurrentHashMap<Long, WebSocket>();

	public static int onlineCount = 0;//在线人数

	private Long userid;//用户id

	private Session session;

	private List<Friend> friendList;

	private static AccountService accountService;

	private static FriendService friendService;

	private static MsgContentService msgContentService;

	private static MsgLogService msgLogService;

	private static AccountGroupMemberService accountGroupMemberService;
	/**
	 * 登录用户的前端公钥Map集合
	 */
	private static Map<Session, String> loginPublicKeyList = new HashMap<Session, String>();
	/**
	 * 建立连接
	 * @param userid
	 * @param session
	 * @throws IOException
	 */
	@OnOpen
	public void onOpen(@PathParam("userid") Long userid,@PathParam("publicKey") String publicKey, Session session) throws IOException {
		this.userid = userid;
		this.session = session;
		this.friendList = friendService.findMyFriendList(userid);
		addOnlineCount();
		clients.put(userid, this);
		System.out.println("成功建立连接，用户ID = 【" + userid + "】,当前在线用户数：" + onlineCount);
		//检查该用户是否有未读消息
		loadUnReadMsg(userid);
		//给所有好友发送上线消息
		onlineNotice(userid);
		//设置前端公钥，因为是url的方式传值，公钥中的/需要进行转换一下，传到后端再转回来，然后将每个用户的前端公钥存储起来
		loginPublicKeyList.put(session,publicKey.replaceAll(",", "/"));

	}


	/**
	 * 当收到信息
	 * @param message
	 * @throws IOException
	 */
	@OnMessage
	public void onMessage(String message) throws Exception {
		//jackson
		ObjectMapper mapper = new ObjectMapper();
		//jackson 序列化和反序列化 date处理
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		//JSON字符串转 HashMap
		HashMap map = mapper.readValue(message, HashMap.class);

		//先解密
		String data = (String) map.get("data");
		String aesKey = (String) map.get("aesKey");

		//后端私钥解密的到AES的key
		byte[] plaintext = RsaUtil.decryptByPrivateKey(Base64.decodeBase64(aesKey), RsaUtil.getPrivateKey());
		aesKey = new String(plaintext);

		//AES解密得到明文data数据
		String decrypt = AesUtil.decrypt(data, aesKey);

		//JSON字符串转 HashMap
		JSONObject hashMap = mapper.readValue(decrypt, JSONObject.class);
		WebSocketMsg webSocketMsg = JSONObject.parseObject(String.valueOf(hashMap), WebSocketMsg.class);
//    	WebSocketMsg webSocketMsg = JSONObject.parseObject(message, WebSocketMsg.class);
		System.out.println(webSocketMsg);
		sendMsg(webSocketMsg);

	}

    /**
     * 发生错误
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }


    public void sendMessageAll(String message) throws IOException {
        for (WebSocket item : clients.values()) {
            item.session.getAsyncRemote().sendText(message);
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocket.onlineCount--;
    }

    public static synchronized Map<Long, WebSocket> getClients() {
        return clients;
    }

    /**
     * 发送消息
     * @param webSocketMsg
     */
    public void sendMsg(WebSocketMsg webSocketMsg){
    	if(WebSocketMsg.CHAT_TYPE_SINGLE.equals(webSocketMsg.getChatType())){
    		//单人聊天
    		sendSingleMsg(webSocketMsg);
    		return;
    	}
    	if(WebSocketMsg.CHAT_TYPE_GROUP.equals(webSocketMsg.getChatType())){
    		//聊天室
    		sendGroupMsg(webSocketMsg);
    		return;
    	}
    	if(WebSocketMsg.CHAT_TYPE_EVENT.equals(webSocketMsg.getChatType())){
    		//事件
    		if(WebSocketMsg.MSG_TYPE_REFRESH_FRIEND_LIST.equals(webSocketMsg.getMsgType())){
    			//刷新好友列表
    			refreshFriendList(webSocketMsg);
    		}
    		return;
    	}
    }


	/**
     * 发送单人一对一聊天消息
     * @param webSocketMsg
     */
    public void sendSingleMsg(WebSocketMsg webSocketMsg){
    	WebSocket webSocket = clients.get(webSocketMsg.getToId());
    	Friend friend = null;
    	if(webSocket != null){
    		friend = webSocket.getFriend(webSocketMsg.getFromId());
    	}else{
    		List<Friend> myFriendList = friendService.findMyFriendList(webSocketMsg.getToId());
    		for(Friend f: myFriendList){
    			if(f.getFriendAccount().getId().longValue() == webSocketMsg.getFromId().longValue()){
    				friend = f;
    				break;
    			}
    		}
    	}
    	if(friend == null){
    		//表示对方删除了你
			webSocketMsg.setMsgType(WebSocketMsg.MSG_TYPE_NOTICE);
			webSocketMsg.setToId(userid);
			webSocketMsg.setMsg("你还不是对方的好友，请先加好友！");
			session.getAsyncRemote().sendText(JSONObject.toJSONString(webSocketMsg));
			return;
    	}
		if(friend.getStatus() == Friend.FRIEND_STATUS_BLOCK){
    		//表示对方拉黑了你
    		webSocketMsg.setMsgType(WebSocketMsg.MSG_TYPE_NOTICE);
    		webSocketMsg.setToId(userid);
    		webSocketMsg.setMsg("你已被对方拉黑，无法发送消息！");
    		session.getAsyncRemote().sendText(JSONObject.toJSONString(webSocketMsg));
    		return;
    	}

    	webSocketMsg.setFromId(friend.getId());
    	String extAttr = friend.getRemark() + ";" + friend.getFriendAccount().getHeadPic();
    	//将成员的头像和昵称放在附加字段中
    	webSocketMsg.setExtAttr(extAttr);
    	if(webSocketMsg.getMsgType() == WebSocketMsg.MSG_TYPE_ONLINE){
    		webSocketMsg.setMsg("您的好友【" + friend.getRemark() + "】上线啦！");
    	}
    	if(webSocketMsg.getMsgType() == WebSocketMsg.MSG_TYPE_OFFLINE){
    		webSocketMsg.setMsg("您的好友【" + friend.getRemark() + "】下线啦！");
    	}
    	if(webSocket != null){
    		webSocket.session.getAsyncRemote().sendText(JSONObject.toJSONString(webSocketMsg));
    	}

    	if(webSocketMsg.getMsgType() == WebSocketMsg.MSG_TYPE_NOTICE || webSocketMsg.getMsgType() == WebSocketMsg.MSG_TYPE_ONLINE || webSocketMsg.getMsgType() == WebSocketMsg.MSG_TYPE_OFFLINE){
    		//通知信息不需要保存到数据库
    		return;
    	}
    	//消息保存到数据库
    	saveMsgLog(accountService.find(webSocketMsg.getToId()),saveMsgContent(webSocketMsg));
    }

    /**
     * 发送聊天室信息
     * @param webSocketMsg
     */
    public void sendGroupMsg(WebSocketMsg webSocketMsg){
    	//首先根据聊天室id获取聊天室成员
    	List<AccountGroupMember> accountGroupMemberList = accountGroupMemberService.findByGroup(webSocketMsg.getToId());
    	if(accountGroupMemberList == null){
    		//表示该聊天室已解散
			webSocketMsg.setMsgType(WebSocketMsg.MSG_TYPE_NOTICE);
			webSocketMsg.setToId(userid);
			webSocketMsg.setMsg("该聊天室已被聊天室主解散！");
			session.getAsyncRemote().sendText(JSONObject.toJSONString(webSocketMsg));
			return;
    	}
    	AccountGroupMember accountGroupMember = getAccountGroupMember(accountGroupMemberList,webSocketMsg.getFromId());
    	if(accountGroupMember == null){
    		webSocketMsg.setMsgType(WebSocketMsg.MSG_TYPE_NOTICE);
			webSocketMsg.setToId(userid);
			webSocketMsg.setMsg("您已被聊天室主移除该聊天室，聊天室成员将不能接受您发送的消息！");
			session.getAsyncRemote().sendText(JSONObject.toJSONString(webSocketMsg));
			return;
    	}
		if("1".equals(accountGroupMember.getMeStatus())){
			webSocketMsg.setMsgType(WebSocketMsg.MSG_TYPE_NOTICE);
			webSocketMsg.setToId(userid);
			webSocketMsg.setMsg("您已被禁言，聊天室成员将不能接受您发送的消息！");
			session.getAsyncRemote().sendText(JSONObject.toJSONString(webSocketMsg));
			return;
		}
    	//说明这个聊天室成员在线
		String extAttr = accountGroupMember.getNickname() + ";" + accountGroupMember.getMember().getHeadPic();
		//将发送消息成员的头像和昵称放在附加字段中
		webSocketMsg.setExtAttr(extAttr);
		MsgContent msgContent = saveMsgContent(webSocketMsg);
    	//遍历聊天室成员
    	for(AccountGroupMember ag : accountGroupMemberList){
    		//排除给自己发消息
    		if(ag.getMember().getId().longValue() != webSocketMsg.getFromId().longValue()){
    			WebSocket webSocket = clients.get(ag.getMember().getId());
    			if(webSocket != null){
    				webSocket.session.getAsyncRemote().sendText(JSONObject.toJSONString(webSocketMsg));
    			}
    			//消息保存到数据库
    	    	saveMsgLog(ag.getMember(),msgContent);
    		}
    	}
    }

    /**
     * 根据账号id获取好友id
     * @param userId
     * @return
     */
    private Friend getFriend(Long userId){
    	for(Friend f : friendList){
    		if(f.getFriendAccount().getId().longValue() == userId.longValue()){
    			return f;
    		}
    	}
    	return null;
    }

    /**
     * 加载未读消息
     * @param userId
     */
    private void loadUnReadMsg(Long userId){
    	List<MsgLog> msgLogs = msgLogService.findByAccountIdAndStatus(userId, MsgLog.MSG_STATUS_UNREAD);
    	for(MsgLog msgLog : msgLogs){
    		this.session.getAsyncRemote().sendText(JSONObject.toJSONString(msgLog.getMsgContent()));
    		msgLog.setStatus(MsgLog.MSG_STATUS_READ);
    		//标记为已读
    		msgLogService.updateById(msgLog);
    	}
    }

    /**
     * 从聊天室中获取发送者成员
     * @param accountGroupMemberList
     * @param id
     * @return
     */
    private AccountGroupMember getAccountGroupMember(List<AccountGroupMember> accountGroupMemberList,Long id){
    	for(AccountGroupMember ag : accountGroupMemberList){
    		if(ag.getMember().getId().longValue() == id.longValue()){
    			return ag;
			}
    	}
    	return null;
    }

    /**
     * 消息内容保存到数据库
     * @param webSocketMsg
     * @return
     */
    private  MsgContent saveMsgContent(WebSocketMsg webSocketMsg){
    	MsgContent msgContent = new MsgContent();
    	msgContent.setAttachSize(webSocketMsg.getAttachSize());
    	msgContent.setAttachUrl(webSocketMsg.getAttachUrl());
    	msgContent.setChatType(webSocketMsg.getChatType());
    	msgContent.setExtAttr(webSocketMsg.getExtAttr());
    	msgContent.setFromId(webSocketMsg.getFromId());
    	msgContent.setMsg(webSocketMsg.getMsg());
    	msgContent.setMsgType(webSocketMsg.getMsgType());
    	msgContent.setToId(webSocketMsg.getToId());
		msgContentService.insert(msgContent);
    	return msgContent;
    }

    /**i
     * 保存消息记录
     * @param
     * @param msgContent
     */
    private void saveMsgLog(Account account,MsgContent msgContent){
    	MsgLog msgLog = new MsgLog();
    	msgLog.setAccount(account);
    	msgLog.setMsgContent(msgContent);
    	WebSocket webSocket = clients.get(account.getId());
    	msgLog.setStatus(webSocket == null ? MsgLog.MSG_STATUS_UNREAD : MsgLog.MSG_STATUS_READ);
    	msgLogService.insert(msgLog);
    }

    /**
     * 聊天室发用户上线通知
     * @param userid
     */
    public void onlineNotice(Long userid) {
		//首先遍历该用户的所有好友
		for(Friend friend : friendList){
			//获取在线的好友
			WebSocket webSocket = clients.get(friend.getFriendAccount().getId());
			if(webSocket != null){
				//表示这个好友在线
				WebSocketMsg webSocketMsg = new WebSocketMsg();
				webSocketMsg.setMsgType(WebSocketMsg.MSG_TYPE_ONLINE);
				webSocketMsg.setToId(friend.getFriendAccount().getId());
				webSocketMsg.setFromId(userid);
				sendSingleMsg(webSocketMsg);
			}
		}
	}

    /**
     * 聊天室发用户下线通知
     * @param userid
     */
    public void offlineNotice(Long userid) {
    	//首先遍历该用户的所有好友
    	for(Friend friend : friendList){
    		//获取在线的好友
    		WebSocket webSocket = clients.get(friend.getFriendAccount().getId());
    		if(webSocket != null){
    			//表示这个好友在线，发送消息
    			WebSocketMsg webSocketMsg = new WebSocketMsg();
    			webSocketMsg.setMsgType(WebSocketMsg.MSG_TYPE_OFFLINE);
    			webSocketMsg.setToId(friend.getFriendAccount().getId());
    			webSocketMsg.setFromId(userid);
    			sendSingleMsg(webSocketMsg);
    		}
    	}
    }

    /**
     * 刷新好友列表
     * @param webSocketMsg
     */
	public void refreshFriendList(WebSocketMsg webSocketMsg) {
		String msg = webSocketMsg.getMsg();
		if(!StringUtils.isEmpty(msg)){
			String[] split = msg.split(",");
			webSocketMsg.setMsg("您的好友列表有更新啦，快来看看吧！");
			for(String id : split){
				Long uid = Long.valueOf(id);
				WebSocket webSocket = clients.get(uid);
				if(webSocket != null){
					webSocket.friendList = friendService.findMyFriendList(uid);
					clients.put(uid, webSocket);
					webSocket.session.getAsyncRemote().sendText(JSONObject.toJSONString(webSocketMsg));
				}
			}
		}
		//更新自己的好友列表
		friendList = friendService.findMyFriendList(userid);
		session.getAsyncRemote().sendText(JSONObject.toJSONString(webSocketMsg));
	}

    @Autowired
    public void setFriendService(FriendService friendService){
    	WebSocket.friendService = friendService;
    }

    @Autowired
    public void setMsgContentService(MsgContentService msgContentService){
    	WebSocket.msgContentService = msgContentService;
    }

    @Autowired
    public void setMsgLogService(MsgLogService msgLogService){
    	WebSocket.msgLogService = msgLogService;
    }

    @Autowired
    public void setAccountGroupMemberService(AccountGroupMemberService accountGroupMemberService){
    	WebSocket.accountGroupMemberService = accountGroupMemberService;
    }

    @Autowired
    public void setAccountService(AccountService accountService){
    	WebSocket.accountService = accountService;
    }
}
