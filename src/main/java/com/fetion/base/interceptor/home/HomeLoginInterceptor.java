package com.fetion.base.interceptor.home;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fetion.base.entity.admin.User;
import com.fetion.base.entity.common.Account;
import com.fetion.base.enumpackage.SystemTypeEnum;
import com.fetion.base.util.FrontUtil;
import com.fetion.base.util.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.alibaba.fastjson.JSON;
import com.fetion.base.bean.CodeMsg;
import com.fetion.base.config.AppConfig;
import com.fetion.base.constant.SessionConstant;
import com.fetion.base.util.StringUtil;

/**
 * 前台登录拦截器
 * @author 卞宇轩
 *
 */
@Component
public class HomeLoginInterceptor implements HandlerInterceptor{

	private Logger log = LoggerFactory.getLogger(HomeLoginInterceptor.class);
	@Resource
	private FrontUtil frontUtil;
	
	@Override
	public boolean  preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
		String requestURI = request.getRequestURI();
		HttpSession session = request.getSession();
		session.setAttribute(SessionConstant.SESSION_USER_AUTH_KEY, AppConfig.ORDER_AUTH);
//		Object attribute = session.getAttribute(SessionConstant.SESSION_ACCOUNT_LOGIN_KEY);
//		if(attribute == null){
		if (TokenUtil.getAccessToken(SessionConstant.COOKIE_NAME_APP) == null) {
			TokenUtil.createAccessToken(SessionConstant.COOKIE_NAME_APP);
		}
		Account user = frontUtil.getLoginedUser(SystemTypeEnum.APP);
		if (user == null) {
			log.info("用户还未登录或者session失效,重定向到登录页面,当前URL=" + requestURI);
			//首先判断是否是ajax请求
			if(StringUtil.isAjax(request)){
				//表示是ajax请求
				try {
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write(JSON.toJSONString(CodeMsg.USER_SESSION_EXPIRED));
				} catch (IOException e) {

					e.printStackTrace();
				}
				return false;
			}
			//说明是普通的请求，可直接重定向到登录页面
			//用户还未登录或者session失效,重定向到登录页面
			try {
				response.sendRedirect("/home/index/login");
			} catch (IOException e) {

				e.printStackTrace();
			}
			return false;
		}
		request.setAttribute(SessionConstant.SESSION_ACCOUNT_LOGIN_KEY, user);
		log.info("该请求符合前台登录要求，放行" + requestURI);
		return true;
	}
}
