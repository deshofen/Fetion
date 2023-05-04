package com.fetion.base.mapper.common;

import java.util.List;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.fetion.base.entity.common.MsgLog;

/**
 * 消息记录数据库处理层
 *
 * @author 卞宇轩
 */
@Mapper
public interface MsgLogMapper extends BaseMapper<MsgLog> {




	List<MsgLog> findByAccountIdAndStatus(@Param("accountId") Long accountId, @Param("status") int status);

	List<MsgLog> findByMsgContentId(@Param("msgContentId") Long msgContentId);


	MsgLog findById(Long id);
}
