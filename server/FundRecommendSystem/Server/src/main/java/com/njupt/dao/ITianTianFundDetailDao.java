package com.njupt.dao;

import com.njupt.entity.TianTianFundDetail;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ITianTianFundDetailDao extends MongoRepository<TianTianFundDetail, String> {

    TianTianFundDetail findBySubAccountNoEquals(String subAccount);
}
