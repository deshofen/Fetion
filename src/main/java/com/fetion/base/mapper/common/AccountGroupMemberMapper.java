package com.fetion.base.mapper.common;
/**
 * 聊天会话数据库操作层
 */

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fetion.base.entity.common.AccountGroupMember;

@Mapper
public interface AccountGroupMemberMapper extends BaseMapper<AccountGroupMember> {

	List<AccountGroupMember> findByAccountGroupId(@Param("accountGroupId") Long accountGroupId);

	List<AccountGroupMember> findByMemberId(@Param("memberId") Long memberId);



	AccountGroupMember findByAccountGroupIdAndMemberId(@Param("accountGroupId") Long accountGroupId, @Param("memberId") Long memberId);


	AccountGroupMember findbById(Long id);

	void deleteByGroup(@Param("accountGroupId") Long accountGroupId);

}
