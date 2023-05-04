package com.fetion.base.service.common;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 聊天会话service
 */
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fetion.base.mapper.common.AccountGroupMemberMapper;
import com.fetion.base.entity.common.AccountGroupMember;

@Service
@Transactional
public class AccountGroupMemberService extends ServiceImpl<AccountGroupMemberMapper, AccountGroupMember> {

	@Autowired
	private AccountGroupMemberMapper accountGroupMemberMapper;

	/**
	 * 新增或编辑聊天室
	 * @param accountGroupMember
	 * @return
	 */
	public int insert(AccountGroupMember accountGroupMember){
		return accountGroupMemberMapper.insert(accountGroupMember);
	}

	/**
	 * 获取聊天室成员
	 * @param type
	 * @param id
	 * @return
	 */
	public List<AccountGroupMember> findByGroup(Long id){
		return accountGroupMemberMapper.findByAccountGroupId(id);
	}

	/**
	 * 获取某个用户参与的所有聊天室
	 * @param id
	 * @return
	 */
	public List<AccountGroupMember> findByMember(Long id){
		return accountGroupMemberMapper.findByMemberId(id);
	}

	/**
	 * 根据聊天室和用户id查询
	 * @param accountGroupId
	 * @param memberId
	 * @return
	 */
	public AccountGroupMember findByAccountGroupAndMember(Long accountGroupId,Long memberId){
		return accountGroupMemberMapper.findByAccountGroupIdAndMemberId(accountGroupId,memberId);
	}

	/**
	 * 根据id删除
	 * @param id
	 */
	public void delete(Long id){
		accountGroupMemberMapper.deleteById(id);
	}

	/**
	 * 根据聊天室id删除
	 * @param accountGroupId
	 */
	@Transactional
	public void deleteByAccountGroupId(Long accountGroupId){
		accountGroupMemberMapper.deleteById(accountGroupId);
	}

	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public AccountGroupMember find(Long id){
		return accountGroupMemberMapper.findbById(id);
	}

}
