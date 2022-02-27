package com.njupt.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;


@Data
public class RequestRegister {

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Length(min = 6, message = "密码长度不能少于6位")
    private String password;

    @NotBlank(message = "确认密码不能为空")
    @Length(min = 6, message = "确认密码长度不能少于6位")
    @JsonProperty("again_password")
    private String againPassword;
}
