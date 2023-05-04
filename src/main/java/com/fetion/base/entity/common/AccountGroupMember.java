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
 * 聊天室成员实体类
 * @author 卞宇轩
 *
 */
@Data
@TableName(value="account_group_member")
public class AccountGroupMember extends BaseEntity{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public static final int FRIEND_MSG_STATUS_ENABLE = 1;//正常提醒

	public static final int FRIEND_MSG_STATUS_MUTE = 0;//消息免打扰


	@TableField(exist = false)
	private AccountGroup accountGroup;//所属聊天室

	@TableField(exist = false)
	private Account member;//用户

	@TableField(value="account_group_id")
	private Long accountGroupId;
	@TableField(value="member_id")
	private Long memberId;

	@ValidateEntity(required=false)
	@TableField(value="msg_status")
	private int msgStatus = FRIEND_MSG_STATUS_ENABLE;//默认正常


	@ValidateEntity(required=false)
	@TableField(value="nickname")
	private String nickname;//聊天室昵称


	@TableField(value="m_status")
	private String meStatus;//默认正常


}
