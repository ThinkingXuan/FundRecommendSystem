package com.njupt.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "base_fund_info")
public class BaseFund {
    /*
        {
            "_id" :ObjectId("603a56a61f1b0b6c6617c5ed"),
                "plan_code" :"CSI1021",
                "follow_num" :NumberLong(60633),
                "plan_name" :"钉钉宝90天组合",
                "manager_name" :"银行螺丝钉",
                "yield" :2.84,
                "max_drawdown" :0.33,
                "sharpe" :2.58,
                "sales" :"1139756730",
                "all_yield" :"3.97",
                "found_days" :NumberLong(516),
                "fund_type" :NumberLong(1),
                "from" :NumberLong(2),
                "final_prior":112.11
        }
    */
    private String _id;

    @JsonProperty(value = "plan_code")
    @Field(value = "plan_code")
    private String planCode;

    @JsonProperty(value = "follow_num")
    @Field(value = "follow_num")
    private long followNum;

    @JsonProperty(value = "plan_name")
    @Field(value = "plan_name")
    private String planName;

    @JsonProperty(value = "manager_name")
    @Field(value = "manager_name")
    private String managerName;

    private Double yield;

    @JsonProperty(value = "max_drawdown")
    @Field(value = "max_drawdown")
    private Double maxDrawdown;

    private Double sharpe;

    private String sales;

    @JsonProperty(value = "all_yield")
    @Field(value = "all_yield")
    private String allYield;

    @JsonProperty(value = "found_days")
    @Field(value = "found_days")
    private long foundDays;

    @JsonProperty(value = "fund_type")
    @Field(value = "fund_type")
    private long fundType;

    private long from;

    @JsonProperty(value = "manager_type")
    @Field(value = "manager_type")
    private long managerType;

    @JsonProperty(value = "final_prior")
    @Field(value = "final_prior")
    private Double finalPrior;
}
