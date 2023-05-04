package com.fetion.base;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
/**
 * 项目入口启动文件
 *
 */
@SpringBootApplication
@ServletComponentScan
@MapperScan("com.fetion.base.mapper")
public class App 
{
    public static void main( String[] args )
    {
    	 SpringApplication.run(App.class, args);
    }
}
