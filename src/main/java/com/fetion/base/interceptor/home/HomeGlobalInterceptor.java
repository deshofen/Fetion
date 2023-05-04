package com.fetion.base.interceptor.home;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 前台全局拦截器
 * @author 卞宇轩
 *
 */
@Component
public class HomeGlobalInterceptor implements HandlerInterceptor{

	
	@Override
	public boolean  preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
		
		
		return true;
	}
}
