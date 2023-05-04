package com.fetion.base.controller.common;

import com.fetion.base.service.common.NewTableService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @author 卞宇轩
 * @date 2023-04-01
 */
@RequestMapping("demo")
@Controller
public class NewTableController {

    @Resource
    private NewTableService newTableService;

    @RequestMapping(value = "/test")
    public void test() {
        newTableService.getTitle(123L);
        newTableService.list();
    }
}
