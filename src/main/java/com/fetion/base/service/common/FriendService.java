package com.fetion.base.service.common;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fetion.base.entity.common.Friend;
import com.fetion.base.mapper.common.FriendMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fetion.base.mapper.common.FriendMapper;
import com.fetion.base.entity.common.Friend;
import org.springframework.transaction.annotation.Transactional;

/**
 * 好友service
 * @author 卞宇轩
 *
 */
@Service
public class FriendService extends ServiceImpl<FriendMapper, Friend> {

	@Autowired
	private FriendMapper friendMapper;

	/**
	 * 更新或新增
	 * @return
	 */
	public int insert(Friend friend){
		return friendMapper.insert(friend);
	}

	/**
	 * 根据id查找
	 * @return
	 */
	public Friend find(Long id){
		return baseMapper.findById(id);
	}

	/**
	 * 获取我的好友列表
	 * @return
	 */
	public List<Friend> findMyFriendList(Long id){
		return friendMapper.findByAccountId(id);
	}

	/**
	 * 根据id删除
	 * @param id
	 */
	public void delete(Long id){
		friendMapper.deleteById(id);
	}

	/**
	 * 根据id删除
	 */
	@Transactional
	public void deleteFlag(String flag){
		friendMapper.deleteFlag(flag);
	}

	public List<Friend> findAll(Long id) {
		return friendMapper.findAllAccount(id);
	}
}
