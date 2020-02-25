package com.easyride.easyrideuserservice.repository;

import com.easyride.easyrideuserservice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IUserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);

}

