package com.njupt.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.sql.Date;
import java.util.List;

@Data
@Document(collection = "danjuan_fund_growth")
public class FundGrowth {
    @Field(value = "plan_code")
    @JsonProperty(value = "plan_code")
    private String planCode;

    private List<Fund_nav_growth> fund_nav_growth;
    private List<Growth_lines> growth_lines;
}


@Data
 class Fund_nav_growth {

    private Date date;
    private String nav;
    private String percentage;
    private String point_flag;
    private String than_value;
    private String value;
}

@Data
 class Growth_lines {
    private String line_color;
    private String line_key;
    private String line_name;

}

