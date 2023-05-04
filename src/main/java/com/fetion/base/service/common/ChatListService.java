package com.fetion.base.service.common;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fetion.base.entity.common.ChatList;
import com.fetion.base.mapper.common.ChatListMapper;
import com.fetion.base.mapper.common.MsgContentMapper;
import com.fetion.base.entity.common.MsgContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.fetion.base.bean.PageBean;
import com.fetion.base.mapper.common.ChatListMapper;
import com.fetion.base.entity.common.ChatList;

import java.util.List;
import java.util.Objects;

@Service
public class ChatListService extends ServiceImpl<ChatListMapper, ChatList> {

    @Autowired
    private static ChatListMapper ChatListMapper;

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public ChatList find(Long id){
        return ChatListMapper.selectById(id);
    }


    /**
     * 添加/编辑操作
     * @param account
     * @return
     */
    public int insert(ChatList ChatList){
        return ChatListMapper.insert(ChatList);
    }

    /**
     * 返回总数
     * @return
     */
    public long total(){
        return ChatListMapper.selectCount(null);
    }

    /**
     * 获取消息列表
     * @param ChatList
     * @param pageBean
     * @return
     */
    public PageBean<ChatList> findList(ChatList ChatList,PageBean<ChatList> pageBean){

        Page pageInfo = new Page(pageBean.getCurrentPage()-1, pageBean.getPageSize());
        QueryWrapper queryWrapper = new QueryWrapper<>();
        if (!Objects.isNull(ChatList.getMsg())){
            queryWrapper.eq("msg",ChatList.getMsg());
        }
        queryWrapper.orderByDesc("create_time");
        baseMapper.selectPage(pageInfo,queryWrapper);
        pageBean.setContent(pageInfo.getRecords());
        pageBean.setTotal(pageInfo.getTotal());
        pageBean.setTotalPage(pageInfo.getPages());
        return pageBean;
    }


    public  List<ChatList> findAll(QueryWrapper queryWrapper) {

        return baseMapper.selectList(queryWrapper);
    }

    public static List<ChatList> singleChatList(Long id) {
        return ChatListMapper.singleChatList(id);
    }
}
