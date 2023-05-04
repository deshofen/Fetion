package com.fetion.base.mapper.common;

/**
 * 聊天会话数据库操作层
 */
import java.util.List;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fetion.base.entity.common.MsgLog;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.apache.ibatis.annotations.Mapper;
import com.fetion.base.entity.common.ChatList;
@Mapper
public interface ChatListMapper extends BaseMapper<ChatList> {






    List<ChatList> singleChatList(@Param("id")Long id);
}