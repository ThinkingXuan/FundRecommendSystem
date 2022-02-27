package com.njupt.common;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * 响应状态
 */

@Getter
@AllArgsConstructor
public enum ResponseCode {

    SUCCESS(200,"success"),
    ERROR(400,"error");

    // 响应状态码
    private final int statusCode;

    // 提示信息
    private final String message;

}
