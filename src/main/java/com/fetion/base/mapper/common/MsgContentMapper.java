package com.fetion.base.mapper.common;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.fetion.base.entity.common.MsgContent;

/**
 * 消息内容数据库处理层
 * @author 卞宇轩
 *
 */
@Mapper
public interface MsgContentMapper extends BaseMapper<MsgContent> {



}
