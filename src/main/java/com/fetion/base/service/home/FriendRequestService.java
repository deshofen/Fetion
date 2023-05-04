package com.fetion.base.service.home;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fetion.base.entity.home.FriendRequest;
import com.fetion.base.mapper.home.FriendRequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fetion.base.mapper.home.FriendRequestMapper;
import com.fetion.base.entity.common.Account;
import com.fetion.base.entity.home.FriendRequest;
/**
 * 好友请求service
 * @author 卞宇轩
 *
 */
@Service
public class FriendRequestService extends ServiceImpl<FriendRequestMapper, FriendRequest> {

	@Autowired
	private FriendRequestMapper friendRequestMapper;
	
	/**
	 * 更新或新增好友请求
	 * @param friendRequest
	 * @return
	 */
	public int insert(FriendRequest friendRequest){
		return friendRequestMapper.insert(friendRequest);
	}
	
	/**
	 * 根据发出者、接受者查询
	 * @param sender
	 * @param reciever
	 * @return
	 */
	public FriendRequest findBySenderAndReciever(Account sender,Account reciever){
		return friendRequestMapper.findBySenderAndReciever(sender, reciever);
	}
	
	/**
	 * 根据第查询
	 * @param id
	 * @return
	 */
	public FriendRequest find(Long id){
		return friendRequestMapper.findById(id);
	}
	
	/**
	 * 根据接受者查询
	 * @param reciever
	 * @return
	 */
	public List<FriendRequest> findByReciever(Account reciever){
		return friendRequestMapper.findByRecieverOrderByUpdateTimeDesc(reciever);
	}
	
	/**
	 * 删除好友请求
	 * @param id
	 */
	public void delete(Long id){
		friendRequestMapper.deleteById(id);
	}
	
	/**
	 * 获取新的好友添加请求数
	 * @param recieverId
	 * @return
	 */
	public Integer getNewFriendRequestCount(Long recieverId){
		return friendRequestMapper.getFriendRequestCount(recieverId, FriendRequest.FRIEND_REQUEST_STATUS_WAITING);
	}
}
