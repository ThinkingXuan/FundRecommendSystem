package com.njupt.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReturnMessage {
    private int type;
    private String message;
    private Object obj;


    public ReturnMessage(int type, String message) {
        this.type = type;
        this.message = message;
    }


    public static ReturnMessage OfReturn(int type, String message) {
        return new ReturnMessage(type, message);
    }

    public static ReturnMessage OfReturn(int type, String message, Object o) {
        return new ReturnMessage(type, message, o);
    }
}

