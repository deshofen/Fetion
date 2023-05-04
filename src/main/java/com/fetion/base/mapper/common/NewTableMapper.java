package com.fetion.base.mapper.common;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fetion.base.entity.common.NewTable;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NewTableMapper extends BaseMapper<NewTable> {

    String getTitle(Long id);
}
