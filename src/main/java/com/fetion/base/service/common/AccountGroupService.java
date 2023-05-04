package com.fetion.base.service.common;
import java.util.List;
import java.util.Objects;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fetion.base.mapper.common.AccountGroupMemberMapper;
import com.fetion.base.mapper.home.GroupRequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
/**
 * 聊天会话service
 */
import org.springframework.stereotype.Service;

import com.fetion.base.bean.PageBean;
import com.fetion.base.mapper.common.AccountGroupMapper;
import com.fetion.base.entity.common.AccountGroup;

@Service
public class AccountGroupService extends ServiceImpl<AccountGroupMapper, AccountGroup> {

	@Autowired
	private AccountGroupMapper accountGroupMapper;
	@Autowired
	private AccountGroupMemberMapper accountGroupMemberMapper;
	@Autowired
	private GroupRequestMapper groupRequestMapper;
	/**
	 * 新增或编辑聊天室
	 * @param accountGroup
	 * @return
	 */
	public int insert(AccountGroup accountGroup){
		return accountGroupMapper.insert(accountGroup);
	}

	/**
	 * 获取我创建的所有聊天室列表
	 * @param type
	 * @param id
	 * @return
	 */
	public List<AccountGroup> findMyGroups(Long id){
		return accountGroupMapper.findByAdminId(id);
	}
	public List<AccountGroup> findPubGroups(int type){
		return accountGroupMapper.findPubGroups(type);
	}

	/**
	 * 分页查询聊天室列表
	 * @param accountGroup
	 * @param pageBean
	 * @return
	 */
	public PageBean<AccountGroup> findList(AccountGroup accountGroup,PageBean<AccountGroup> pageBean){


		Page pageInfo = new Page(pageBean.getCurrentPage()-1, pageBean.getPageSize());
		QueryWrapper queryWrapper = new QueryWrapper<>();
		if (!Objects.isNull(accountGroup.getName())){
			queryWrapper.eq("name",accountGroup.getName());
		}
		baseMapper.selectPage(pageInfo,queryWrapper);
		pageBean.setContent(pageInfo.getRecords());
		pageBean.setTotal(pageInfo.getTotal());
		pageBean.setTotalPage(pageInfo.getPages());
		return pageBean;
	}

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public AccountGroup findById(Long id){
		return accountGroupMapper.findById(id);
	}

	/**
	 * 根据id删除
	 * @param id
	 */
	public void delete(Long id){
		accountGroupMemberMapper.deleteByGroup(id);
		groupRequestMapper.deleteByAccountGroupId(id);
		accountGroupMapper.deleteById(id);
	}

	/**
	 * 返回总数
	 * @return
	 */
	public long total(){
		return accountGroupMapper.selectCount(null);
	}
}
