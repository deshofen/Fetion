package com.fetion.base.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fetion.base.annotion.ValidateEntity;


/**
 * 用户聊天室实体类
 * @author 卞宇轩
 *
 */
@Data
@TableName(value="account_group")
public class AccountGroup extends BaseEntity {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public static final int GROUP_MIN_PEOPLE = 1;//创建聊天室最小人数

	public static final int GROUP_MAX_PEOPLE = 65535;//创建聊天室最大人数

	@TableField(exist = false)
	private Account admin;//所属用户，即聊天室主

	@TableField(value="admin_id")
	private Long adminId;//所属用户，即聊天室主

	@TableField(value="name")
	private String name;//聊天室名称

	@TableField(value="picture")
	private String picture;//聊天室封面图片

	@TableField(value="info")
	private String info;//聊天室介绍

	@TableField(value="notice")
	private String notice;//聊天室公告

	@TableField(value="min_people")
	private int minPeople = GROUP_MIN_PEOPLE;//当为聊天室时最小人数

	@TableField(value="max_people")
	private int maxPeople = GROUP_MAX_PEOPLE;//当为聊天室时最大人数

	@TableField(value="cur_people")
	private int curPeople = 0;//当前聊天室人数

	@TableField(value="type")
	private String type ;//类型




}
