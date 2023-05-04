package com.fetion.base.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * 消息内容实体类
 * @author 卞宇轩
 *
 */
@Data
@TableName(value="msg_content")
public class MsgContent extends BaseEntity{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String CHAT_TYPE_SINGLE = "single";//单人聊天
	
	public static final String CHAT_TYPE_GROUP = "group";//聊天室消息
	
	public static final String MSG_TYPE_TEXT = "text";//文本消息
	
	public static final String MSG_TYPE_IMG = "img";//图片消息
	
	public static final String MSG_TYPE_FILE = "file";//文件消息
	
	public static final String MSG_TYPE_VIDEO = "video";//视频件消息
	
	public static final String MSG_TYPE_AUDIO = "audio";//音频消息

	@TableField(value="to_id")
	private Long toId;//消息接受者id

	@TableField(value="from_id")
	private Long fromId;//消息发送者id

	@TableField(value="chat_type")
	private String chatType;//聊天类型

	@TableField(value="msg_type")
	private String msgType;//消息类型

	@TableField(value="content")
	private String msg;//消息内容

	@TableField(value="ext_attr")
	private String extAttr;//消息附加字段

	@TableField(value="attach_url")
	private String attachUrl;//附件地址

	@TableField(value="attach_size")
	private long attachSize;//附件大小

	
}
