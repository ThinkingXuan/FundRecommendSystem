package com.njupt.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document(collection = "danjuan_fund_summary")
public class FundSummary {

    private String _id;
    @Field(value = "plan_code")
    @JsonProperty(value = "plan_code")
    private String planCode;
    private List<Position> position;
    private int status;
}

@Data
 class Position {
    private String color;
    private double percent;
    private String type;
    private String type_desc;

}
