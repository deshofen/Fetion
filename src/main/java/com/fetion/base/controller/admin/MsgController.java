package com.fetion.base.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fetion.base.bean.MsgVo;
import com.fetion.base.bean.PageBean;
import com.fetion.base.bean.Result;
import com.fetion.base.entity.common.Account;
import com.fetion.base.entity.common.AccountGroup;
import com.fetion.base.entity.common.Friend;
import com.fetion.base.entity.common.MsgContent;
import com.fetion.base.service.common.AccountGroupService;
import com.fetion.base.service.common.AccountService;
import com.fetion.base.service.common.FriendService;
import com.fetion.base.service.common.MsgContentService;
import com.fetion.base.service.common.MsgLogService;

/**
 * 后台消息管理控制器
 * @author 卞宇轩
 *
 */
@RequestMapping("/admin/msg")
@Controller
public class MsgController {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private FriendService friendService;
	
	@Autowired
	private AccountGroupService accountGroupService;
	
	@Autowired
	private MsgContentService msgContentService;
	
	@Autowired
	private MsgLogService msgLogService;
	
	/**
	 * 消息列表页面
	 * @param model
	 * @param msgContent
	 * @param pageBean
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list(Model model,MsgContent msgContent,PageBean<MsgContent> pageBean){
		model.addAttribute("title", "消息列表");
		model.addAttribute("msg", msgContent.getMsg());
		model.addAttribute("pageBean", msgContentService.findList(msgContent, pageBean));
		return "admin/msg_content/list";
	}
	
	/**
	 * 查看消息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/view_info",method=RequestMethod.POST)
	@ResponseBody
	public Result<MsgVo> viewInfo(@RequestParam(name="id",required=true)Long id,
			@RequestParam(name="fromId",required=true)Long fromId,
			@RequestParam(name="toId",required=true)Long toId,
			@RequestParam(name="type",required=true)String type){
		MsgVo msgVo = new MsgVo();
		msgVo.setMsgLogs(msgLogService.findByMsgContentId(id));
		if(MsgContent.CHAT_TYPE_SINGLE.equals(type)){
			Friend friend = friendService.find(fromId);
			if(friend != null){
				msgVo.setSender(friend.getFriendAccount().getUsername());
			}
			Account account = accountService.find(toId);
			if(account != null){
				msgVo.setReciever(account.getUsername());
			}
			return Result.success(msgVo);
		}
		//表示聊天室消息
		Account account = accountService.find(fromId);
		if(account != null){
			msgVo.setSender(account.getUsername());
		}
		AccountGroup accountGroup = accountGroupService.findById(toId);
		if(accountGroup != null){
			msgVo.setReciever(accountGroup.getName());
		}
		return Result.success(msgVo);
	}
}
