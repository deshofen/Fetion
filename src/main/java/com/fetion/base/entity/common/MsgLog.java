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
 * 消息记录实体类
 * @author 卞宇轩
 *
 */
@Data
@TableName(value="msg_log")
public class MsgLog extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int MSG_STATUS_READ = 1;//已读
	
	public static final int MSG_STATUS_UNREAD = 0;//未读
	

	@TableField(exist = false)
	private Account account;//所属用户

	@TableField(exist = false)
	private MsgContent msgContent;//消息实体

	@TableField(value="account_id")
	private Long accountId;

	@TableField(value="msg_content_id")
	private Long msgContentId;

	@ValidateEntity(required=false)
	@Column(name="status",length=1)
	private int status = MSG_STATUS_UNREAD;//默认正常


	
	
}
