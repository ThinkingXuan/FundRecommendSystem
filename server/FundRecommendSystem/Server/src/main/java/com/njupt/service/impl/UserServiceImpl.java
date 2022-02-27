package com.njupt.service.impl;

import com.njupt.common.ReturnMessage;
import com.njupt.common.ReturnType;
import com.njupt.dao.IUserDao;
import com.njupt.entity.User;
import com.njupt.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    IUserDao userDao;

    @Override
    public User login(String username, String password) {
        return userDao.findByUsernameAndPasswordEquals(username, password);
    }

    @Override
    public ReturnMessage register(String username, String password) {
        // 查看此用户是否存在
        User nowUser = userDao.findByUsernameEquals(username);
        if (null != nowUser) {
            return ReturnMessage.OfReturn(ReturnType.ERROR.getType(), "用户已存在");
        }
        String id = userDao.save(new User(username, password)).get_id();
        if ("".equals(id)) {
            return ReturnMessage.OfReturn(ReturnType.ERROR.getType(), "注册失败");
        }
        return ReturnMessage.OfReturn(ReturnType.SUCCESS.getType(), "注册成功");
    }
}
