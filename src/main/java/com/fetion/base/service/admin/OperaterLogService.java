package com.fetion.base.service.admin;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fetion.base.entity.admin.OperaterLog;
import com.fetion.base.mapper.admin.OperaterLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.stereotype.Service;

import com.fetion.base.bean.PageBean;
import com.fetion.base.mapper.admin.OperaterLogMapper;
import com.fetion.base.entity.admin.OperaterLog;
import com.fetion.base.entity.admin.User;
import com.fetion.base.util.SessionUtil;

/**
 * 后台操作类 数据库操作service
 * @author 卞宇轩
 *
 */
@Service
public class OperaterLogService extends ServiceImpl<OperaterLogMapper, OperaterLog> {


	/**
	 * 添加/修改操作日志，当id不为空时，修改，id为空时自动新增一条记录
	 * @param operaterLog
	 * @return
	 */
	public int insert(OperaterLog operaterLog){
		return baseMapper.insert(operaterLog);
	}

	/**
	 * 获取指定条数的操作日志列表
	 * @param size
	 * @return
	 */
	public List<OperaterLog> findLastestLog(int size){
		return baseMapper.findLastestLog(size);
	}

	/**
	 * 根据id查询单条数据
	 * @param id
	 * @return
	 */
	public OperaterLog findById(Long id){
		return baseMapper.selectById(id);
	}

	/**
	 * 返回所有的记录
	 * @return
	 */
	public List<OperaterLog> findAll(){
		return baseMapper.selectList(null);
	}

	/**
	 * 删除单条记录
	 * @param id
	 */
	public void delete(Long id){
		baseMapper.deleteById(id);
	}

	/**
	 * 清空整张表
	 */
	public void deleteAll(){
		baseMapper.delete(null);
	}

	/**
	 * 操作日志添加
	 * @param operater
	 * @param content
	 */
	public void add(String operater,String content){
		OperaterLog operaterLog = new OperaterLog();
		operaterLog.setOperator(operater);
		operaterLog.setContent(content);
		insert(operaterLog);
	}

	/**
	 * 操作日志添加
	 * @param content
	 */
	public void add(String content){
		User loginedUser = SessionUtil.getLoginedUser();
		add(loginedUser == null ? "未知(获取登录用户失败)" : loginedUser.getUsername(), content);
	}

	/**
	 * 分页查找日志
	 * @param operaterLog
	 * @param pageBean
	 * @return
	 */
	public PageBean<OperaterLog> findList(OperaterLog operaterLog,PageBean<OperaterLog> pageBean){
		Page pageInfo = new Page(pageBean.getCurrentPage()-1, pageBean.getPageSize());

		baseMapper.selectPage(pageInfo,null);
		pageBean.setContent(pageInfo.getRecords());
		pageBean.setTotal(pageInfo.getTotal());
		pageBean.setTotalPage(pageInfo.getPages());

		return pageBean;
	}
	/**
	 * 希尔排序
	 **/
	public void sort(OperaterLog[] arr) {
		int j;
		for (int gap = arr.length / 2; gap >  0; gap /= 2) {
			for (int i = gap; i < arr.length; i++) {
				OperaterLog tmp = arr[i];
				for (j = i; j >= gap && tmp.getCreateTime().compareTo(arr[j - gap].getCreateTime()) < 0; j -= gap) {
					arr[j] = arr[j - gap];
				}
				arr[j] = tmp;
			}
		}
	}

	/**
	 * 操作日志总数
	 * @return
	 */
	public long total(){
		return baseMapper.selectCount(null);
	}
}
