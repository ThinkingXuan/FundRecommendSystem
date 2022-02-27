package com.njupt.dao;

import com.njupt.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;


/*MongoRepository
  第一个参数	  绑定的实体类
  第二个参数	，该实体类主键的类型
  */
public interface IUserDao extends MongoRepository<User, String> {

    User findByUsernameAndPasswordEquals(String username, String password);

    User findByUsernameEquals(String username);
}
