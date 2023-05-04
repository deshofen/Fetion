package com.fetion.base.entity.admin;
/**
 * 后台操作日志记录表
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;


import com.baomidou.mybatisplus.annotation.TableName;
import com.fetion.base.entity.common.BaseEntity;
import lombok.Data;

@Data
@TableName(value="operater_log")
public class OperaterLog extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="operator",nullable=false,length=18)
	private String operator;//操作者
	
	@Column(name="content",nullable=false,length=128)
	private String content;//操作内容
	


	

	
	
}
