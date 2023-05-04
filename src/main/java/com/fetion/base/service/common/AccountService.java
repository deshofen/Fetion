package com.fetion.base.service.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fetion.base.entity.common.Account;
import com.fetion.base.mapper.common.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fetion.base.bean.PageBean;
import com.fetion.base.mapper.common.AccountMapper;
import com.fetion.base.entity.common.Account;

import java.util.List;
import java.util.Objects;

/**
 * 用户管理service
 * @author 卞宇轩
 *
 */
@Service
public class AccountService extends ServiceImpl<AccountMapper, Account> {

	@Autowired
	private AccountMapper accountMapper;

	/**
	 * 根据用户id查询
	 * @param id
	 * @return
	 */
	public Account find(Long id){
		return accountMapper.selectById(id);
	}

	/**
	 * 按照用户名查找用户
	 * @param username
	 * @return
	 */
	public Account findByUsername(String username){
		return accountMapper.findByUsername(username);
	}

	/**
	 * 用户添加/编辑操作
	 * @param account
	 * @return
	 */
	public int insert(Account account){
		return accountMapper.insert(account);
	}

	/**
	 * 分页查询用户列表
	 * @param account
	 * @param pageBean
	 * @return
	 */
	public PageBean<Account> findList(Account account,PageBean<Account> pageBean){

		Page pageInfo = new Page(pageBean.getCurrentPage()-1, pageBean.getPageSize());
		QueryWrapper queryWrapper = new QueryWrapper<>();
		if (!Objects.isNull(account.getUsername())){
			queryWrapper.eq("username",account.getUsername());
		}
		baseMapper.selectPage(pageInfo,queryWrapper);
		pageBean.setContent(pageInfo.getRecords());
		pageBean.setTotal(pageInfo.getTotal());
		pageBean.setTotalPage(pageInfo.getPages());
		return pageBean;
	}

	/**
	 * 判断用户名是否存在，添加和编辑均可判断
	 * @param username
	 * @param id
	 * @return
	 */
	public boolean isExistUsername(String username,Long id){
		Account account = accountMapper.findByUsername(username);
		if(account != null){
			//表示用户名存在，接下来判断是否是编辑用户的本身
			if(account.getId().longValue() != id.longValue()){
				return true;
			}
		}
		return false;
	}

	/**
	 * 按照用户id删除
	 * @param id
	 */
	public void delete(Long id){
		accountMapper.deleteById(id);
	}

	/**
	 * 返回用户总数
	 * @return
	 */
	public long total(){
		return accountMapper.selectCount(null);
	}

	public List<Long> findAll(Long id) {
		return accountMapper.findAllAccount(id);
	}
}
