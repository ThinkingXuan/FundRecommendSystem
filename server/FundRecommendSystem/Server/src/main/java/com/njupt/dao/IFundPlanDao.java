package com.njupt.dao;

import com.njupt.entity.FundDetail;
import com.njupt.entity.FundPlan;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IFundPlanDao extends MongoRepository<FundPlan, String> {
    FundPlan findByPlanCodeEquals(String planCode);

}
