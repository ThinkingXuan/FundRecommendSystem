package com.njupt.dao;

import com.njupt.entity.BaseFund;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface IBaseFundInfoDao extends PagingAndSortingRepository<BaseFund, String> {

    List<BaseFund> findByFundTypeEqualsAndFinalPriorIsGreaterThanEqual(long fundType, double finalPrior);

}
