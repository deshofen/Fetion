package com.fetion.base.service.common;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fetion.base.mapper.common.NewTableMapper;
import com.fetion.base.entity.common.NewTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewTableService extends ServiceImpl<NewTableMapper, NewTable> {

    @Autowired
    private NewTableMapper newTableMapper;

    public String getTitle(Long id) {
        return newTableMapper.getTitle(id);
    }
}
