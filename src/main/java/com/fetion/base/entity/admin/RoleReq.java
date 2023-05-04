package com.fetion.base.entity.admin;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fetion.base.annotion.ValidateEntity;
import com.fetion.base.entity.common.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import java.util.List;

/**
 * 后台角色实体类
 * @author 卞宇轩
 *
 */
@Data
public class RoleReq extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int ADMIN_ROLE_STATUS_ENABLE = 1;//角色状态正常可用
	public static final int ADMIN_ROLE_STATUS_UNABLE = 0;//角色状态不可用
	
	@ValidateEntity(required=true,requiredLeng=true,minLength=1,maxLength=18,errorRequiredMsg="角色名称不能为空!",errorMinLengthMsg="角色名称长度需大于1!",errorMaxLengthMsg="角色名称长度不能大于18!")

	private String name;//角色名称
	

	private List<String> authorities;//角色所对应的权限（菜单）列表
	

	private int status = ADMIN_ROLE_STATUS_ENABLE;//角色状态,默认可用

	private String remark;//角色备注


	
	
	
	
	
	
}
