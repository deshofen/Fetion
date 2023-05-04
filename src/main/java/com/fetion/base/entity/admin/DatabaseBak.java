package com.fetion.base.entity.admin;
/**
 * 数据库备份记录实体类
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


import com.fetion.base.entity.common.BaseEntity;

@Data
@TableName(value="database_bak")
public class DatabaseBak extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="filename",nullable=false,length=32)
	private String filename;//备份的文件名
	
	@Column(name="filepath",nullable=false,length=128)
	private String filepath;//备份的文件路径


	@Override
	public String toString() {
		return "DatabaseBak [filename=" + filename + ", filepath=" + filepath
				+ "]";
	}

	
	
	
	
	
	
	
	
}
