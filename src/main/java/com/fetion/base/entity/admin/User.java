package com.fetion.base.entity.admin;

import javax.persistence.Column;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fetion.base.annotion.ValidateEntity;
import com.fetion.base.entity.common.BaseEntity;
import lombok.Data;
/**
 * 后台用户实体类
 * @author 卞宇轩
 *
 */
@Data
@TableName(value="user")
public class User extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int USER_SEX_MAN = 1;//性别男
	
	private static final int USER_SEX_WOMAN = 2;//性别女
	
	private static final int USER_SEX_UNKONW = 0;//性别未知
	
	public static final int ADMIN_USER_STATUS_ENABLE = 1;//用户状态正常可用
	public static final int ADMIN_USER_STATUS_UNABLE = 0;//用户状态不可用

	@TableField(exist = false)
	private Role role;//用户所属角色

	@TableField(value="role_id")
	private Long roleId;
	@ValidateEntity(required=true,requiredLeng=true,minLength=4,maxLength=18,errorRequiredMsg="用户名不能为空!",errorMinLengthMsg="用户名长度需大于4!",errorMaxLengthMsg="用户名长度不能大于18!")
	@TableField(value="username")
	private String username;//用户名
	
	@ValidateEntity(required=true,requiredLeng=true,minLength=4,maxLength=32,errorRequiredMsg="密码不能为空！",errorMinLengthMsg="密码长度需大于4!",errorMaxLengthMsg="密码长度不能大于32!")
	@TableField(value="password")
	private String password;//登录密码
	
	@ValidateEntity(required=false)
	@TableField(value="status")
	private int status = ADMIN_USER_STATUS_ENABLE;//用户状态,默认可用
	
	@ValidateEntity(required=false)
	@TableField(value="head_pic")
	private String headPic;//用户头像
	
	@ValidateEntity(required=false)
	@TableField(value="sex")
	private int sex = USER_SEX_UNKONW;//用户性别
	
	@ValidateEntity(required=false)
	@TableField(value="mobile")
	private String mobile;//用户手机号
	
	@ValidateEntity(required=false)
	@TableField(value="email")
	private String email;//用户邮箱



	@Override
	public String toString() {
		return "User [role=" + role + ", username=" + username + ", password="
				+ password + ", status=" + status + ", headPic=" + headPic
				+ ", sex=" + sex + ", mobile=" + mobile + ", email=" + email
				+ "]";
	}

	
	
	
}
