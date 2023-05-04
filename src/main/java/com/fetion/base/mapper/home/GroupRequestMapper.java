package com.fetion.base.mapper.home;
/**
 * 好友请求数据库处理
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fetion.base.entity.common.Account;
import com.fetion.base.entity.home.GroupRequest;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface GroupRequestMapper extends BaseMapper<GroupRequest> {


	GroupRequest findBySenderAndReciever(@Param("sender") Account sender, @Param("reciever") Account reciever);

	List<GroupRequest> findByRecieverOrderByUpdateTimeDesc(@Param("reciever") Account reciever);

	Integer getGroupRequestCount(@Param("recieverId") Long recieverId, @Param("status") int status);

    GroupRequest findById(@Param("id") Long id);

    void deleteByAccountGroupId(Long accountGroupId);
}
