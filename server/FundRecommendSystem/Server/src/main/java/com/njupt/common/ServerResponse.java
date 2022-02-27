package com.njupt.common;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 状态码 消息 数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServerResponse {

    @JsonProperty(value = "status_code")
    private int statusCode;
    private String message;
    private Object data;

    private ServerResponse(int code, String msg) {
        this.statusCode = code;
        this.message = msg;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return this.statusCode == ResponseCode.SUCCESS.getStatusCode();
    }

    public static ServerResponse ofSuccess() {
        return new ServerResponse(ResponseCode.SUCCESS.getStatusCode(), ResponseCode.SUCCESS.getMessage());
    }

    public static ServerResponse ofSuccess(String msg) {
        return new ServerResponse(ResponseCode.SUCCESS.getStatusCode(), msg);
    }
    public static ServerResponse ofSuccess(Object o) {
        return new ServerResponse(ResponseCode.SUCCESS.getStatusCode(), ResponseCode.SUCCESS.getMessage(), o);
    }

    public static ServerResponse ofSuccess(String msg, Object o) {
        return new ServerResponse(ResponseCode.SUCCESS.getStatusCode(), msg, o);
    }

    public static ServerResponse ofSuccess(int code, String msg, Object o) {
        return new ServerResponse(code, msg, o);
    }


    public static ServerResponse ofError() {
        return new ServerResponse(ResponseCode.ERROR.getStatusCode(), ResponseCode.ERROR.getMessage());
    }

    public static ServerResponse ofError(String msg) {
        return new ServerResponse(ResponseCode.ERROR.getStatusCode(), msg);
    }

    public static ServerResponse ofError(Object o) {
        return new ServerResponse(ResponseCode.ERROR.getStatusCode(), ResponseCode.ERROR.getMessage(), o);
    }

    public static ServerResponse ofError(String msg, Object o) {
        return new ServerResponse(ResponseCode.ERROR.getStatusCode(), msg, o);
    }

    public static ServerResponse ofError(int code, String msg, Object o) {
        return new ServerResponse(code, msg, o);
    }

}
