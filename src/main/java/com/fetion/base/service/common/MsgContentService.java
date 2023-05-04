package com.fetion.base.service.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fetion.base.entity.common.ChatList;
import com.fetion.base.entity.common.MsgContent;
import com.fetion.base.mapper.common.MsgContentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.fetion.base.bean.PageBean;
import com.fetion.base.mapper.common.MsgContentMapper;
import com.fetion.base.entity.common.MsgContent;

import java.util.List;
import java.util.Objects;

/**
 * 消息内容管理service
 * @author 卞宇轩
 *
 */
@Service
public class MsgContentService extends ServiceImpl<MsgContentMapper, MsgContent> {

	@Autowired
	private MsgContentMapper msgContentMapper;

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public MsgContent find(Long id){
		return msgContentMapper.selectById(id);
	}


	/**
	 * 添加/编辑操作
	 * @param account
	 * @return
	 */
	public int insert(MsgContent msgContent){
		return msgContentMapper.insert(msgContent);
	}

	/**
	 * 返回总数
	 * @return
	 */
	public long total(){
		return msgContentMapper.selectCount(null);
	}

	/**
	 * 获取消息列表
	 * @param msgContent
	 * @param pageBean
	 * @return
	 */
	public PageBean<MsgContent> findList(MsgContent msgContent,PageBean<MsgContent> pageBean){

		Page pageInfo = new Page(pageBean.getCurrentPage()-1, pageBean.getPageSize());
		QueryWrapper queryWrapper = new QueryWrapper<>();
		if (!Objects.isNull(msgContent.getMsg())){
			queryWrapper.eq("content",msgContent.getMsg());
		}
		queryWrapper.orderByDesc("create_time");
		baseMapper.selectPage(pageInfo,queryWrapper);
		pageBean.setContent(pageInfo.getRecords());
		pageBean.setTotal(pageInfo.getTotal());
		pageBean.setTotalPage(pageInfo.getPages());
		return pageBean;
	}

	public  List<MsgContent> findAll(QueryWrapper queryWrapper) {

		return baseMapper.selectList(queryWrapper);
	}
}
