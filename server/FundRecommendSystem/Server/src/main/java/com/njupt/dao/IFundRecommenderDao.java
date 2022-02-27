package com.njupt.dao;

import com.njupt.entity.BaseFund;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IFundRecommenderDao  extends MongoRepository<BaseFund,String> {
}
