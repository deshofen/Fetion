package com.fetion.base.entity.home;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fetion.base.annotion.ValidateEntity;
import com.fetion.base.entity.common.Account;
import com.fetion.base.entity.common.AccountGroup;
import com.fetion.base.entity.common.BaseEntity;


import javax.persistence.*;
import lombok.Data;
/**
 * 前台加聊天室请求实体类
 * @author 卞宇轩
 *
 */
@Data
@TableName(value="group_request")
public class GroupRequest extends BaseEntity{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public static final int FRIEND_REQUEST_STATUS_WAITING = 0;//待处理

	public static final int FRIEND_REQUEST_STATUS_AGREE = 1;//已同意

	public static final int FRIEND_REQUEST_STATUS_REFUSE = -1;//已拒绝

	public static final int FRIEND_REQUEST_STATUS_EXPIRED = 2;//已过期


	@TableField(exist = false)
	private Account sender;//请求发出者


	@TableField(exist = false)
	private Account reciever;//请求接受者


	@TableField(value="sender_account_id")
	private Long senderAccountId;
	@TableField(value="reciever_account_id")
	private Long recieverAccountId;

	@TableField(exist = false)
	private AccountGroup accountGroup;//所属聊天室

	@TableField(value="account_group_id")
	private Long accountGroupId;

	@ValidateEntity(required=false)
	@TableField(value="status")
	private int status = FRIEND_REQUEST_STATUS_WAITING;//默认待处理请求


	@ValidateEntity(required=false)
	@TableField(value="remark")
	private String remark;//备注


}
