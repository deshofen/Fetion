package com.fetion.base.service.admin;
/**
 * 后台角色操作service
 */
import java.util.List;
import java.util.Objects;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fetion.base.entity.admin. Role;
import com.fetion.base.entity.admin.RoleReq;
import com.fetion.base.mapper.admin. RoleMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fetion.base.bean.PageBean;
import com.fetion.base.mapper.admin.RoleMapper;
import com.fetion.base.entity.admin.Role;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@Transactional(rollbackFor=Exception.class)
public class RoleService extends ServiceImpl< RoleMapper,  Role> {
	
	@Autowired
	private RoleMapper roleMapper;
	
	/**
	 * 角色添加/编辑
	 * @param role
	 * @return
	 */
	public int insert(RoleReq role){
		Role ro = new Role();
		BeanUtils.copyProperties(role,ro);
		roleMapper.insert(ro);

		return roleMapper.insertAuthorities(role.getAuthorities(),ro.getId());
	}
	
	/**
	 * 获取所有的角色列表
	 * @return
	 */
	public List<Role> findAll(){
		return roleMapper.selectList(null);
	}
	
	/**
	 * 分页按角色名称搜索角色列表
	 * @param role
	 * @param pageBean
	 * @return
	 */
	public PageBean<Role> findByName(Role role,PageBean<Role> pageBean){


		Page pageInfo = new Page(pageBean.getCurrentPage()-1, pageBean.getPageSize());
		QueryWrapper queryWrapper = new QueryWrapper<>();
		if (!Objects.isNull(role.getName())){
			queryWrapper.eq("name",role.getName());
		}
		baseMapper.selectPage(pageInfo,queryWrapper);
		pageBean.setContent(pageInfo.getRecords());
		pageBean.setTotal(pageInfo.getTotal());
		pageBean.setTotalPage(pageInfo.getPages());
		return pageBean;
	}
	
	/**
	 * 根据id查询角色
	 * @param id
	 * @return
	 */
	public Role find(Long id){
		return roleMapper.getRoleById(id);
	}
	
	/**
	 * 根据id删除一条记录
	 * @param id
	 */
	public void delete(Long id){
		roleMapper.deleteAuthorities(id);
		roleMapper.deleteById(id);

	}

	public int updateRole(Role role, List<String> authorities) {

		baseMapper.updateById(role);

		roleMapper.deleteAuthorities(role.getId());

		return roleMapper.insertAuthorities(authorities,role.getId());

	}
}
