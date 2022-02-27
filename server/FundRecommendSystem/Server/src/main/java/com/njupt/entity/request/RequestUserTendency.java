package com.njupt.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * 用户基金组合倾向
 */
@Data
public class RequestUserTendency {

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "投资金额不能为空")
    @JsonProperty("invest_amount")
    private String investAmount;

    @Min(value = 0, message = "投资时长不能小于0")
    @JsonProperty("invest_duration")
    private int investDuration;

    @Min(value = 0, message = "投资类型错误")
    @Max(value = 2, message = "投资类型错误")
    private int type;

    @Min(value = 0, message = "投资收益不能小于0")
    private int profit;
    @Min(value = 0, message = "回撤不能小于0")
    private int dropdown;

}
