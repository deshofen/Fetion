package com.fetion.base.mapper.admin;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Mapper;
import com.fetion.base.entity.admin.DatabaseBak;

/**
 * 数据库备份处理层
 *
 * @author 卞宇轩
 */
@Mapper
public interface DatabaseBakMapper extends BaseMapper<DatabaseBak> {




}
