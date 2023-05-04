package com.fetion.base.service.home;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fetion.base.entity.home.GroupRequest;
import com.fetion.base.mapper.home.GroupRequestMapper;
import com.fetion.base.mapper.home.GroupRequestMapper;
import com.fetion.base.entity.common.Account;
import com.fetion.base.entity.home.GroupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 好友请求service
 * @author 卞宇轩
 *
 */
@Service
public class GroupRequestService extends ServiceImpl<GroupRequestMapper, GroupRequest> {

	@Autowired
	private GroupRequestMapper groupRequestMapper;

	/**
	 * 更新或新增好友请求
	 * @param GroupRequest
	 * @return
	 */
	public int insert(GroupRequest GroupRequest){
		return groupRequestMapper.insert(GroupRequest);
	}

	/**
	 * 根据发出者、接受者查询
	 * @param sender
	 * @param reciever
	 * @return
	 */
	public GroupRequest findBySenderAndReciever(Account sender,Account reciever){
		return groupRequestMapper.findBySenderAndReciever(sender, reciever);
	}

	/**
	 * 根据第查询
	 * @param id
	 * @return
	 */
	public GroupRequest find(Long id){
		return groupRequestMapper.findById(id);
	}

	/**
	 * 根据接受者查询
	 * @param reciever
	 * @return
	 */
	public List<GroupRequest> findByReciever(Account reciever){
		return groupRequestMapper.findByRecieverOrderByUpdateTimeDesc(reciever);
	}

	/**
	 * 删除好友请求
	 * @param id
	 */
	public void delete(Long id){
		groupRequestMapper.deleteById(id);
	}

	/**
	 * 获取新的好友添加请求数
	 * @param recieverId
	 * @return
	 */
	public Integer getNewGroupRequestCount(Long recieverId){
		return groupRequestMapper.getGroupRequestCount(recieverId, GroupRequest.FRIEND_REQUEST_STATUS_WAITING);
	}
}
