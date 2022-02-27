package com.njupt.dao;

import com.njupt.entity.FundDetail;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IFundDetailDao  extends MongoRepository<FundDetail, String> {
    FundDetail findByPlanCodeEquals(String planCode);
}
