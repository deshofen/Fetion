package com.fetion.base.mapper.common;
/**
 * 聊天会话数据库操作层
 */

import java.util.List;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.fetion.base.entity.common.AccountGroup;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountGroupMapper extends BaseMapper<AccountGroup> {


//	AccountGroup find(@Param("id") Long id);

	List<AccountGroup> findByAdminId(@Param("adminId") Long adminId);

	List<AccountGroup> findPubGroups(@Param("type") int type);

    AccountGroup findById(Long id);
}
