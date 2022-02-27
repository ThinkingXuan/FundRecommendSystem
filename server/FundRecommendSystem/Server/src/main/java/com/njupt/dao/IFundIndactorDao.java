package com.njupt.dao;

import com.njupt.entity.FundDetail;
import com.njupt.entity.FundIndicator;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IFundIndactorDao extends MongoRepository<FundIndicator, String> {
    FundIndicator findByPlanCodeEquals(String planCode);

}
