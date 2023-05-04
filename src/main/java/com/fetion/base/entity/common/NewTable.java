package com.fetion.base.entity.common;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
/**
 * 新的表对象
 *
 * @author 卞宇轩
 */
@TableName(value="new_table")
public class NewTable extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;
    private String title;

    @TableField(exist = false)
    private String nonTableField;

}
