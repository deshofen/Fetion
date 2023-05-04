package com.fetion.base.mapper.admin;
/**
 * 后台操作日志类数据库操作层
 */
import java.util.List;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.fetion.base.entity.admin.OperaterLog;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface OperaterLogMapper extends BaseMapper<OperaterLog> {
	

	
	/**
	 * 获取最近的指定条数的操作日志
	 * @param size
	 * @return
	 */
	List<OperaterLog> findLastestLog(@Param("size")int size);
}
