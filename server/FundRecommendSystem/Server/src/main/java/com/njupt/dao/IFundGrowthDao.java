package com.njupt.dao;

import com.njupt.entity.FundGrowth;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IFundGrowthDao extends MongoRepository<FundGrowth, String> {
    FundGrowth findByPlanCodeEquals(String planCode);
}
