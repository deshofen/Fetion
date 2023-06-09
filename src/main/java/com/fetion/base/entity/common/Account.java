package com.fetion.base.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


import com.fetion.base.annotion.ValidateEntity;
import lombok.Data;
/**
 * 前台用户实体类
 * @author 卞宇轩
 *
 */
@Data
@TableName(value="base_account")
public class Account extends BaseEntity{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private static final int USER_SEX_MAN = 1;//性别男

	private static final int USER_SEX_WOMAN = 2;//性别女

	private static final int USER_SEX_UNKONW = 0;//性别未知

	public static final int ACOUNT_USER_STATUS_ENABLE = 1;//用户状态正常可用
	public static final int ACOUNT_USER_STATUS_UNABLE = 0;//用户状态不可用

	public static final String ACOUNT_CHAT_STATUS_ONLINE = "active";//用户在线
	public static final String ACOUNT_CHAT_STATUS_OFFLINE = "status-offline";//用户不在线
	public static final String ACOUNT_CHAT_STATUS_AWAY = "status-away";//用户离开

	@ValidateEntity(required=true,requiredLeng=true,minLength=4,maxLength=18,errorRequiredMsg="用户名不能为空!",errorMinLengthMsg="用户名长度需大于4!",errorMaxLengthMsg="用户名长度不能大于18!")
	@Column(name="username",nullable=false,length=18,unique=true)
	private String username;//用户名

	@ValidateEntity(required=true,requiredLeng=true,minLength=4,maxLength=32,errorRequiredMsg="密码不能为空！",errorMinLengthMsg="密码长度需大于4!",errorMaxLengthMsg="密码长度不能大于32!")
	@Column(name="password",nullable=false,length=32)
	private String password;//登录密码

	@ValidateEntity(required=false)
	@Column(name="status",length=1)
	private int status = ACOUNT_USER_STATUS_ENABLE;//用户状态,默认可用

	@ValidateEntity(required=false)
	@Column(name="head_pic",length=128)
	private String headPic;//用户头像

	@ValidateEntity(required=false)
	@Column(name="sex",length=1)
	private int sex = USER_SEX_UNKONW;//用户性别

	@ValidateEntity(required=false)
	@Column(name="type",length=1)
	private String type ;//用户类型

	@ValidateEntity(required=false)
	@Column(name="mobile",length=12)
	private String mobile;//用户手机号

	@ValidateEntity(required=false)
	@Column(name="email",length=32)
	private String email;//用户邮箱

	@ValidateEntity(required=false)
	@Column(name="info",length=128)
	private String info;//用户简介

	@ValidateEntity(required=false)
	@Column(name="chat_status",length=18)
	private String chatStatus = ACOUNT_CHAT_STATUS_OFFLINE;//用户聊天状态

}
