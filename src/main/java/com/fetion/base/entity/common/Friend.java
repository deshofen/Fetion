package com.fetion.base.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fetion.base.annotion.ValidateEntity;
import lombok.Data;
/**
 * 好友实体类
 * @author 卞宇轩
 *
 */
@Data
@TableName(value="friend")
public class Friend extends BaseEntity{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public static final int FRIEND_STATUS_ENABLE = 1;//正常

	public static final int FRIEND_STATUS_BLOCK = -1;//拉黑

	public static final int FRIEND_MSG_STATUS_ENABLE = 1;//正常提醒

	public static final int FRIEND_MSG_STATUS_MUTE = 0;//消息免打扰



	@TableField(exist = false)
	private Account account;//所属用户


	@TableField(exist = false)
	private Account friendAccount;//好友用户

	@TableField(value="account_id")
	private Long accountId;
	@TableField(value="friend_account_id")
	private Long friendAccountId;

	@ValidateEntity(required=false)
	@TableField(value="status")
	private int status = FRIEND_STATUS_ENABLE;//默认正常

	@ValidateEntity(required=false)
	@TableField(value="msg_status")
	private int msgStatus = FRIEND_MSG_STATUS_ENABLE;//默认正常


	@ValidateEntity(required=false)
	@TableField(value="remark")
	private String remark;//备注

	@ValidateEntity(required=false)
	@TableField(value="flag")
	private String flag;//表示


}
