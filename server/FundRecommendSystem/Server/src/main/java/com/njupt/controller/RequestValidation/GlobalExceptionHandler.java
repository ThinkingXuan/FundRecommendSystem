package com.njupt.controller.RequestValidation;

import com.njupt.common.ResponseCode;
import com.njupt.common.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice("com.njupt.controller")
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 忽略参数异常处理器
     *
     * @param e 忽略参数异常
     * @return ResponseResult
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ServerResponse parameterMissingExceptionHandler(MissingServletRequestParameterException e) {
        logger.error("", e);
        return ServerResponse.ofError("请求参数 " + e.getParameterName() + " 不能为空");
    }

    /**
     * 缺少请求体异常处理器
     *
     * @param e 缺少请求体异常
     * @return ResponseResult
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ServerResponse parameterBodyMissingExceptionHandler(HttpMessageNotReadableException e) {
        logger.error("", e);
        return ServerResponse.ofError("参数体不能为空");
    }

    /**
     * 参数效验异常处理器
     *
     * @param e 参数验证异常
     * @return ResponseInfo
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ServerResponse parameterExceptionHandler(MethodArgumentNotValidException e) {
        logger.error("", e);
        // 获取异常信息
        BindingResult exceptions = e.getBindingResult();
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                // 这里列出了全部错误参数，按正常逻辑，只需要第一条错误即可
                FieldError fieldError = (FieldError) errors.get(0);
                return ServerResponse.ofError(fieldError.getDefaultMessage());
            }
        }
        return ServerResponse.ofError(ResponseCode.ERROR.getStatusCode());
    }

    /**
     * 自定义参数错误异常处理器
     *
     * @param e 自定义参数
     * @return ResponseInfo
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ParamaErrorException.class})
    public ServerResponse paramExceptionHandler(ParamaErrorException e) {
        logger.error("", e);
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (!StringUtils.isEmpty(e.getMessage())) {
            return ServerResponse.ofError(e.getMessage());
        }
        return ServerResponse.ofError(ResponseCode.ERROR.getStatusCode());
    }

    /**
     * 其他异常
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({Exception.class})
    public ServerResponse otherExceptionHandler(Exception e) {
        logger.error("其他异常", e);
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (!StringUtils.isEmpty(e.getMessage())) {
            return ServerResponse.ofError(e.getMessage());
        }
        return ServerResponse.ofError(ResponseCode.ERROR.getStatusCode());
    }
}