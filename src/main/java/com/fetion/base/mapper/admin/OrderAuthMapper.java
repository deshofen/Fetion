package com.fetion.base.mapper.admin;
/**
 * 后台操作日志类数据库操作层
 */
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.springframework.stereotype.Repository;

import com.fetion.base.entity.admin.OrderAuth;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface OrderAuthMapper extends BaseMapper<OrderAuth> {
	
}
