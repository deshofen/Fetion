package com.fetion.base.mapper.home;
/**
 * 好友请求数据库处理
 */

import java.util.List;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.fetion.base.entity.common.Account;
import com.fetion.base.entity.home.FriendRequest;

@Mapper
public interface FriendRequestMapper extends BaseMapper<FriendRequest> {



	FriendRequest findBySenderAndReciever(@Param("sender") Account sender, @Param("reciever") Account reciever);

	List<FriendRequest> findByRecieverOrderByUpdateTimeDesc(@Param("reciever") Account reciever);

	Integer getFriendRequestCount(@Param("recieverId") Long recieverId, @Param("status") int status);

    FriendRequest findById(Long id);
}
