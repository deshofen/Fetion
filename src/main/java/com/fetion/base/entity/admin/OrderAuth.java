package com.fetion.base.entity.admin;
/**
 * 订单验证日志记录表
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;


import com.baomidou.mybatisplus.annotation.TableName;
import com.fetion.base.annotion.ValidateEntity;
import com.fetion.base.entity.common.BaseEntity;
import lombok.Data;

@Data
@TableName(value="base_order_auth")
public class OrderAuth extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ValidateEntity(required=true,requiredLeng=true,minLength=11,maxLength=11,errorRequiredMsg="手机号不能为空!",errorMinLengthMsg="手机号长为11!",errorMaxLengthMsg="手机号长度不能大于11!")
	@Column(name="phone",nullable=false,length=12)
	private String phone;//手机号
	
	@ValidateEntity(required=true,requiredLeng=true,minLength=18,maxLength=18,errorRequiredMsg="订单号不能为空!",errorMinLengthMsg="订单号长度不能小于18!",errorMaxLengthMsg="订单号长度不能大于18!")
	@Column(name="order_sn",unique=true,nullable=false,length=18)
	private String orderSn;//订单编号
	
	@ValidateEntity(required=false)
	@Column(name="mac",length=32)
	private String mac ;//mac



	@Override
	public String toString() {
		return "OrderAuth [phone=" + phone + ", orderSn=" + orderSn + ", mac="
				+ mac + "]";
	}
	
	
	
	
	
}
