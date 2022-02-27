package com.njupt.service;

import com.njupt.common.ReturnMessage;
import com.njupt.entity.User;

public interface IUserService {


    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    User login(String username, String password);

    /**
     * 用户注册
     *
     * @param username
     * @param password
     * @return
     */
    ReturnMessage register(String username, String password);


}
