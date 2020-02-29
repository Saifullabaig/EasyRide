package com.easyride.easyridebookingservice;

//import org.junit.jupiter.api.Test;
import com.easyride.easyridebookingservice.model.Booking;
import com.easyride.easyridebookingservice.model.PaymentStatus;
import com.easyride.easyridebookingservice.repo.BookRepo;
import com.easyride.easyridebookingservice.service.BookingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.annotation.Id;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.UUID;

@SpringBootTest(classes = EasyRideBookingServiceApplication.class)
@TestPropertySource("/application-test.properties")
class EasyRideBookingServiceApplicationTests {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private BookingService bookingService;

    static Booking booking;

    @BeforeAll
    public static void BookingTest() throws Exception {

//            Booking booking= new Booking();
        booking.setBookingId(UUID.randomUUID());
        booking.setDriverId(101);
        booking.setUserId("saifulla");
        booking.setSource("Bangalore");
        booking.setDestination("Goa");
        booking.setPickDate("02/03/2020");
        booking.setPickTime("10:15");
        booking.setPrice(1200.23);
        booking.setDistance(400.34);
        booking.setPaymentStatus(PaymentStatus.SUCCESS);
    }

    @Test
    public void getBookingDetails() throws Exception {
        bookingService.bookTicket(booking);
        UUID x = booking.getBookingId();
        try {
            Assertions.assertEquals(1, bookingService.getBooking(x));
            ;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//            booking = bookingService.getBooking(x);
    }
}