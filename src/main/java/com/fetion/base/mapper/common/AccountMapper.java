package com.fetion.base.mapper.common;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.fetion.base.entity.common.Account;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * 用户数据库处理层
 *
 * @author 卞宇轩
 */
@Mapper
public interface AccountMapper extends BaseMapper<Account> {

	/**
	 * 按照用户名查找用户信息
	 *
	 * @param username
	 * @return
	 */
	public Account findByUsername(@Param("username") String username);



    List<Long> findAllAccount(@Param("id") Long id);
}
