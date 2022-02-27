package com.njupt.controller.RequestValidation;

import com.njupt.common.ResponseCode;
import com.njupt.common.ServerResponse;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import java.util.Set;

/**
 * 手动调用api方法校验对象
 */
public class MyValidationUtils {
    public static ServerResponse validate(@Valid Object user) {
        Set<ConstraintViolation<@Valid Object>> validateSet = Validation.buildDefaultValidatorFactory()
                .getValidator()
                .validate(user, new Class[0]);
        if (!CollectionUtils.isEmpty(validateSet)) {
            String messages = validateSet.stream()
                    .map(ConstraintViolation::getMessage)
                    .reduce((m1, m2) -> m1 + "；" + m2)
                    .orElse("参数输入有误！");
//            throw new IllegalArgumentException(messages);
            return ServerResponse.ofError(messages);
        }
        return ServerResponse.ofError(ResponseCode.ERROR.getStatusCode());
    }
}