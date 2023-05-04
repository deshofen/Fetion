package com.fetion.base.entity.admin;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fetion.base.annotion.ValidateEntity;
import com.fetion.base.entity.common.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.List;

/**
 * 后台角色-菜单实体类
 * @author 卞宇轩
 *
 */
@Data
@TableName(value="role_authorities")
public class RoleAuthorities implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	@TableField(value = "role_id")
	private Long roleId;//

	@TableField(value = "authorities_id")
	private Long authoritiesId;//

	
	
	
	
	
	
}
