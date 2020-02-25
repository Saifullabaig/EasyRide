package com.easyride.easyridebookingservice.repo;

import com.easyride.easyridebookingservice.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.UUID;

@Repository
public interface BookRepo extends MongoRepository<Booking, String> {
    Booking findByBookingId(UUID bookingId);
    ArrayList<Booking> findByUserId(String userId);
}

