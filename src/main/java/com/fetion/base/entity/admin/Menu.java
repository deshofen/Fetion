package com.fetion.base.entity.admin;

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
import com.fetion.base.entity.common.BaseEntity;
import lombok.Data;
/**
 * 后台菜单实体类
 * @author 卞宇轩
 *
 */
@Data
@TableName(value="menu")
public class Menu extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ValidateEntity(required=true,requiredLeng=true,minLength=1,maxLength=18,errorRequiredMsg="菜单名称不能为空!",errorMinLengthMsg="菜单名称长度需大于1!",errorMaxLengthMsg="菜单名称长度不能大于18!")
	@TableField(value="name")
	private String name;//菜单名称

	@TableField(exist = false)
	private Menu parent;//菜单父分类
	
	@ValidateEntity(required=false)
	@TableField(value="url")
	private String url;//菜单url

	@ValidateEntity(required=false)
	@TableField(value="icon")
	private String icon;//菜单图标icon

	@TableField(value="sort")
	private Integer sort = 0;//菜单顺序，默认升序排列,默认是0

	@TableField(value="is_bitton")
	private boolean isButton = false;//是否是按钮

	@TableField(value="is_show")
	private boolean isShow = true;//是否显示

	@TableField(value="parent_id")
	private Long parentId;

	@Override
	public String toString() {
		return "Menu [name=" + name + ", parent=" + parent + ", url=" + url
				+ ", icon=" + icon + ", sort=" + sort + ", isButton="
				+ isButton + ", isShow=" + isShow + "]";
	}

	

	
	
	
}
