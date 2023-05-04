package com.fetion.base.service.admin;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fetion.base.entity.admin.OrderAuth;
import com.fetion.base.mapper.admin.OrderAuthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.fetion.base.mapper.admin.OrderAuthMapper;
import com.fetion.base.entity.admin.OrderAuth;

/**
 * 后台操作类 数据库操作service
 * @author 卞宇轩
 *
 */
@Service
public class OrderAuthService extends ServiceImpl<OrderAuthMapper, OrderAuth> {
	
	@Autowired
	private OrderAuthMapper orderAuthMapper;
	
	/**
	 * 添加/修改操作日志，当id不为空时，修改，id为空时自动新增一条记录
	 * @param operaterLog
	 * @return
	 */
	public int insert(OrderAuth orderAuth){
		return orderAuthMapper.insert(orderAuth);
	}
	
	/**
	 * 获取一条记录
	 * @return
	 */
	public OrderAuth findOne(){
		QueryWrapper queryWrapper = new QueryWrapper<>();
		queryWrapper.orderByDesc("create_time");
		List<OrderAuth> list = baseMapper.selectList(queryWrapper);
		if(list == null || list.size() < 1)return null;
		return list.get(0);
	}
}
