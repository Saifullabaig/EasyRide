package com.easyride.easyridebookingservice.repo;

import com.easyride.easyridebookingservice.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayRepo extends MongoRepository<Payment, String> {
}
