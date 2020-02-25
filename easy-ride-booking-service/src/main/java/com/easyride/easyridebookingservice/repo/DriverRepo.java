package com.easyride.easyridebookingservice.repo;

import com.easyride.easyridebookingservice.model.Driver;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DriverRepo extends MongoRepository<Driver, Integer> {

}

