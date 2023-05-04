package com.fetion.base.mapper.admin;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Mapper;
import com.fetion.base.entity.admin.User;

/**
 * 用户数据库处理层
 * @author 卞宇轩
 *
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
	
	/**
	 * 按照用户名查找用户信息
	 * @param username
	 * @return
	 */
	public User findByUsername(String username);


	
}
