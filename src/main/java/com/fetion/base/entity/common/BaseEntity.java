package com.fetion.base.entity.common;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;


/**
 * 基础实体公共属性
 * @author 卞宇轩
 *
 */
@MappedSuperclass
@Data
public class BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@TableId(value="id",type = IdType.AUTO)
	private Long id;//唯一id

	@Column(name="create_time",nullable=false)
	@TableField(value="create_time" ,fill = FieldFill.INSERT)
	private Date createTime;//创建时间

	@Column(name="update_time",nullable=false)
	@TableField(value="update_time" , fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;//更新时间
	

	
	
}
