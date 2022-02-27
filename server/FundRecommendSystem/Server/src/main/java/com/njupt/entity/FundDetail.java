package com.njupt.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.util.List;

@Data
@Document(collection = "danjuan_fund_detail")
public class FundDetail {
    private String _id;
    private List<Indicator_docs> indicator_docs;
    private List<Items> items;

    @Field(value = "plan_code")
    @JsonProperty(value = "plan_code")
    private String planCode;
}

@Data
class Indicator_docs {
    private List<String> docs;
    private String title;
    private String type;
}

@Data
class Items {
    private String fd_code;
    private String fd_name;
    private double percent;
    private double total_gain_rate;
    private String type;
    private String type_desc;
}
