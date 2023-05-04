package com.fetion.base.util;

import com.alibaba.fastjson.JSON;
import com.fetion.base.constant.SessionConstant;
import com.fetion.base.entity.admin.User;
import com.fetion.base.entity.common.Account;
import com.fetion.base.enumpackage.SystemTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 卞宇轩
 * @date 2023-03-31
 */
@Component
public class FrontUtil {

    @Autowired
    private RedisUtil redisUtil;


    public <T> T getLoginedUser(SystemTypeEnum systemTypeEnum) {
        Object user = redisUtil.get(SystemTypeEnum.ADMIN.equals(systemTypeEnum) ? TokenUtil.getAdminRedisTokenKey() : TokenUtil.getAppRedisTokenKey());
        if (user != null) {
            return JSON.parseObject(user.toString(), SystemTypeEnum.ADMIN.equals(systemTypeEnum) ? User.class : Account.class);
        }
        return null;
    }

    public void setLoginedUser(SystemTypeEnum systemTypeEnum, Object loginedUser) {
        String key = SystemTypeEnum.ADMIN.equals(systemTypeEnum) ? TokenUtil.getAdminRedisTokenKey() : TokenUtil.getAppRedisTokenKey();
        redisUtil.set(key, JSON.toJSONString(loginedUser), SessionConstant.SESSION_TIME_USER_LOGIN);
    }

    public void delLoginedUser(SystemTypeEnum systemTypeEnum) {
        String key = SystemTypeEnum.ADMIN.equals(systemTypeEnum) ? TokenUtil.getAdminRedisTokenKey() : TokenUtil.getAppRedisTokenKey();
        redisUtil.delKeys(key);
    }


}
