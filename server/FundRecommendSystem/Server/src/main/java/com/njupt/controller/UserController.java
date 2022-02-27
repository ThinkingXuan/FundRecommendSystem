package com.njupt.controller;

import com.njupt.common.ReturnMessage;
import com.njupt.common.ReturnType;
import com.njupt.common.ServerResponse;

import com.njupt.entity.request.RequestLogin;
import com.njupt.entity.request.RequestRegister;
import com.njupt.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * 前台用户控制器
 */

@RequestMapping("frs/user")
@RestController
// 相当于 @Controller + @ResponseBody  返回json数据不需要在方法前面加@ResponseBody注解了，但使用@RestController这个注解，就不能返回jsp,html页面，视图解析器无法解析jsp,html页面
public class UserController {


    @Autowired
    IUserService userService;

    @PostMapping("/login")
    public ServerResponse login(@Valid @RequestBody RequestLogin user) {

        // 登录处理
        if (null != userService.login(user.getUsername(), user.getPassword())) {
            return ServerResponse.ofSuccess("登陆成功");
        } else {
            return ServerResponse.ofError("密码或账号错误 ");
        }
    }

    @PostMapping("/register")
    public ServerResponse register(@Valid @RequestBody RequestRegister user) {
        if (!user.getPassword().equals(user.getAgainPassword())) {
            return ServerResponse.ofError("注册失败,两次输入的密码不一致");
        }
        ReturnMessage returnMessage = userService.register(user.getUsername(), user.getPassword());

        if (returnMessage.getType() != ReturnType.SUCCESS.getType()) {
            return ServerResponse.ofError(returnMessage.getMessage());
        }
        return ServerResponse.ofSuccess(returnMessage.getMessage());
    }

}
