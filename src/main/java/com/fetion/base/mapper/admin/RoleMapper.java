package com.fetion.base.mapper.admin;
/**
 * 后台角色数据库操作层
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.fetion.base.entity.admin.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {


    Role getRoleById(Long id);

    int insertAuthorities(@Param("authorities") List<String> authorities, @Param("roleId") Long roleId);

    void deleteAuthorities(@Param("roleId") Long roleId);
}
