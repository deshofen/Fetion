package com.fetion.base.util;

import com.fetion.base.constant.SessionConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author 卞宇轩
 * @date 2023-03-31
 */
public class TokenUtil {

    public static String getAdminRedisTokenKey() {
        String token = SessionConstant.SESSION_USER_LOGIN_KEY + ":" + getAccessToken(SessionConstant.COOKIE_NAME_ADMIN);
        if (token == null) {
            return null;
        }
        return token;
    }

    public static String getAppRedisTokenKey() {
        String token = SessionConstant.SESSION_ACCOUNT_LOGIN_KEY + ":" + getAccessToken(SessionConstant.COOKIE_NAME_APP);
        if (token == null) {
            return null;
        }
        return token;
    }

    public static String getAccessToken(String tokenName) {
        if (StringUtils.isBlank(tokenName)) {
            tokenName = SessionConstant.COOKIE_NAME_ADMIN;
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Cookie cookie = getCookieByName(request, tokenName);
        if (cookie != null) {
            return cookie.getValue();
        } else {
            return (String) request.getAttribute(tokenName);
        }
    }

    public static void createAccessToken(String tokenName) {
        if (StringUtils.isBlank(tokenName)) {
            tokenName = SessionConstant.COOKIE_NAME_ADMIN;
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        String accessToken = UUID.randomUUID().toString();
        addCookie(response, tokenName, accessToken, 0);
        request.setAttribute(tokenName, accessToken);
    }

    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        if (maxAge > 0) cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    public static Cookie getCookieByName(HttpServletRequest request, String name) {
        Map<String, Cookie> cookieMap = ReadCookieMap(request);
        if (cookieMap.containsKey(name)) {
            Cookie cookie = (Cookie) cookieMap.get(name);
            return cookie;
        } else {
            return null;
        }
    }

    private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }
}
