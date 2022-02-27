package com.njupt.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document(collection = "danjuan_fund_indcator")
public class FundIndicator {

    private String _id;
    private String benchmark_code;
    private String benchmark_key;
    private int benchmark_max_drawdown;
    private double benchmark_sharpe;
    private double benchmark_volatility;
    private List<Indicator_docs> indicator_docs;
    private double max_drawdown;
    private String max_drawdown_title;

    @Field(value = "plan_code")
    @JsonProperty(value = "plan_code")
    private String planCode;

    private String plan_key;
    private double sharpe;
    private String sharpe_title;
    private double volatility;
    private String volatility_title;

}