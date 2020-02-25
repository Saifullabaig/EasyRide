package com.easyride.easyridebookingservice.service;

import com.easyride.easyridebookingservice.model.Booking;
import com.easyride.easyridebookingservice.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class BookingService {

    @Autowired
    private BookRepo bookRepo;


    public void bookTicket(Booking booking){
        bookRepo.save(booking);
    }

    public ArrayList<Booking> getTickets(){
        return (ArrayList<Booking>) bookRepo.findAll();
    }

    public Booking getBooking(UUID id){ return bookRepo.findByBookingId(id); }

    public ArrayList<Booking> getTickets(String userId) { return bookRepo.findByUserId(userId);}
}
