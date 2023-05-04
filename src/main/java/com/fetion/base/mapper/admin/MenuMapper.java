package com.fetion.base.mapper.admin;
/**
 * 后台菜单数据库操作层
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Mapper;
import com.fetion.base.entity.admin.Menu;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    Menu getMenuById(Long id);


    List<Menu> findAll();

}
