package com.njupt.dao;


import com.njupt.entity.UserTendency;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IFundTendencyDao extends MongoRepository<UserTendency, String> {

    // 根据用户名查询用户投资倾向信息
    UserTendency findByUsernameEquals(String username);
}
