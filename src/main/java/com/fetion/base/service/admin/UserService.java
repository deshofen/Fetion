package com.fetion.base.service.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fetion.base.entity.admin.User;
import com.fetion.base.mapper.admin.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fetion.base.bean.PageBean;
import com.fetion.base.mapper.admin.UserMapper;
import com.fetion.base.entity.admin.User;

import java.util.Objects;

/**
 * 用户管理service
 * @author 卞宇轩
 *
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> {

	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 根据用户id查询
	 * @param id
	 * @return
	 */
	public User find(Long id){
		return userMapper.selectById(id);
	}
	
	/**
	 * 按照用户名查找用户
	 * @param username
	 * @return
	 */
	public User findByUsername(String username){
//		QueryWrapper queryWrapper = new QueryWrapper<>();
//		queryWrapper.eq("username",username);
//
//		return baseMapper.selectOne(queryWrapper);

		return baseMapper.findByUsername(username);
	}
	
	/**
	 * 用户添加/编辑操作
	 * @param user
	 * @return
	 */
	public int insert(User user){
		return userMapper.insert(user);
	}
	
	/**
	 * 分页查询用户列表
	 * @param user
	 * @param pageBean
	 * @return
	 */
	public PageBean<User> findList(User user,PageBean<User> pageBean){

		Page pageInfo = new Page(pageBean.getCurrentPage()-1, pageBean.getPageSize());
		QueryWrapper queryWrapper = new QueryWrapper<>();
		if (!Objects.isNull(user.getUsername())){
			queryWrapper.eq("username",user.getUsername());
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
		User user = userMapper.findByUsername(username);
		if(user != null){
			//表示用户名存在，接下来判断是否是编辑用户的本身
			if(user.getId().longValue() != id.longValue()){
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
		userMapper.deleteById(id);
	}
	
	/**
	 * 返回用户总数
	 * @return
	 */
	public long total(){
		return userMapper.selectCount(null);
	}
}
