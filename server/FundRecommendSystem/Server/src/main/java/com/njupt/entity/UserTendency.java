package com.njupt.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user_tendency")
public class UserTendency {

    private String _id;

    private String username;

    @JsonProperty("invest_amount")
    private String investAmount;

    @JsonProperty("invest_duration")
    private int investDuration;

    private int type;

    private int profit;

    private int dropdown;
}
