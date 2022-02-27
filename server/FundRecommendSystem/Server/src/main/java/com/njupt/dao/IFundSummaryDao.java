package com.njupt.dao;

import com.njupt.entity.FundSummary;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IFundSummaryDao extends MongoRepository<FundSummary, String> {
    FundSummary findByPlanCodeEquals(String planCode);

}
