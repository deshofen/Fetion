package com.fetion.base.service.admin;
/**
 * 后台菜单操作service
 */
import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fetion.base.entity.admin.Menu;
import com.fetion.base.mapper.admin.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fetion.base.mapper.admin.MenuMapper;
import com.fetion.base.entity.admin.Menu;

@Service
public  class MenuService  extends ServiceImpl<MenuMapper, Menu> {



	/**
	 * 菜单添加/编辑
	 * @param menu
	 * @return
	 */
	public int insert(Menu menu){
		if(menu.getParent() != null){
			menu.setParentId(menu.getParent().getId());
		}
		return baseMapper.insert(menu);
	}

	/**
	 * 获取所有的菜单列表
	 * @return
	 */
	public List<Menu> findAll(){

		return baseMapper.findAll();
	}

	/**
	 * 根据id查询菜单
	 * @param id
	 * @return
	 */
	public Menu find(Long id){
		return baseMapper.getMenuById(id);
	}

	/**
	 * 根据id删除一条记录
	 * @param id
	 */
	public void delete(Long id){
		baseMapper.deleteById(id);
	}
}
