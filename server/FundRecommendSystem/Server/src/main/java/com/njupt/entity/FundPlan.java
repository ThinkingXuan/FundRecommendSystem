package com.njupt.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.sql.Date;
import java.util.List;

@Data
@Document(collection = "danjuan_fund_plan")
public class FundPlan {

    private String _id;
    private String auto_invest_status;
    private int follower_count;
    private Date found_date;
    private int found_days;
    private String growth_day;
    private List<Indicator_docs> indicator_docs;
    private String invest_money_name;
    private int invest_money_type;
    private String invest_time_name;
    private int invest_time_type;
    private String last_buys;
    private String last_remark;
    private String last_sales;
    private long last_trade_date;
    private Date last_trade_date_fmt;
    private String manager_name2;
    private String manager_profile_photo;
    private String min_buy_amount;
    private Op_fund op_fund;

    @Field(value = "plan_code")
    @JsonProperty(value = "plan_code")
    private String planCode;
    private List<Plan_comment> plan_comment;
    private Plan_derived plan_derived;
    private List<Plan_desc> plan_desc;
    private String plan_name2;
    private Plan_rates plan_rates;
    private String sales;
    private String status;
    private int status_count;
    private String type;
    private String all_yield;
    private String yield_middle;
    private String yield_name;
    private String yield_name_middle;
    private String yield_name_t;
    private String yield_t;
}


@Data
class Fund_tags {

    private String category;
    private int level;
    private String name;
}

@Data
class Op_fund {

    private String banner_img;
    private String category;
    private List<Fund_tags> fund_tags;
    private int level;
    private String recom_desc;
    private String risk_tag;
    private String st_code;
    private String type_tag;
    private String yield_type;

}

@Data
class Plan_comment {

    private long created_at;
    private int id;
    private String nick_name;
    private String plan_code;
    private String plan_name;
    private String source;
    private int status;
    private String text;

}

@Data
class Yield_history {

    private String name;
    private String yield;
}

@Data
class Plan_derived {

    private Date end_date;
    private String nav_grbase;
    private String nav_grl1m;
    private String nav_grl1w;
    private String nav_grl1y;
    private String nav_grl3m;
    private String nav_grl6m;
    private String nav_grlty;
    private String nav_grtd;
    private String unit_nav;
    private List<Yield_history> yield_history;

}

@Data
class Plan_desc {

    private String desc;
    private int id;
    private String name;
    private String plan_code;
    private String plan_desc;

}

@Data
class Rate_tables {

    private boolean is_max_close;
    private boolean is_min_close;
    private int min_balance;
    private int rate;
    private String rate_unit;
}

@Data
class Fund_fee_infos {
    private double discount;
    private String fd_code;
    private int fee_rate;
    private List<Rate_tables> rate_tables;
    private String rate_unit;
}

@Data
class Plan_rates {
    private int discount_rate;
    private int fee_rate;
    private List<Fund_fee_infos> fund_fee_infos;
    private String plan_code;
    private int real_rate;
}

