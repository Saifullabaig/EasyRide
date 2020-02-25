package com.easyride.easyridebookingservice.controller;

import com.easyride.easyridebookingservice.model.*;
import com.easyride.easyridebookingservice.service.BookingService;
import com.easyride.easyridebookingservice.service.DriverService;
import com.easyride.easyridebookingservice.service.PaymentService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.UUID;

@RestController
public class BookingController {

    @Autowired
    private DriverService driverService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/driver")
    ResponseEntity<?> postDriver(@RequestBody Driver driver){
        try {
            driverService.saveDriver(driver);
            return new ResponseEntity<>("saved succesfully",HttpStatus.CREATED);
        }catch(Exception ex) {
            return new ResponseEntity<>("error in server",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/driver")
    ResponseEntity<?> getDriver(){
        try {
            return new ResponseEntity<>(driverService.getAllDriver(),HttpStatus.OK);
        }catch(Exception ex) {
            return new ResponseEntity<>("error in server",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/booking")
    ResponseEntity<?> postTicket(@RequestBody Booking booking, HttpServletRequest httpServletRequest){
        try {
             String userId = driverService.loadByUsername(httpServletRequest);
            booking.setUserId(userId);
            booking.setPaymentStatus(PaymentStatus.PENDING);
            booking.setBookingId(UUID.randomUUID());
            bookingService.bookTicket(booking);
            return new ResponseEntity<>(booking.getBookingId().toString(),HttpStatus.OK);
        }catch(Exception ex) {
            System.out.println(ex);
            return new ResponseEntity<>("error in server",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/booking")
    ResponseEntity<?> getTicket(HttpServletRequest httpServletRequest){
        try {
            ArrayList<Ticket> tickets = new ArrayList<>();
            String user = driverService.loadByUsername(httpServletRequest);
            ArrayList<Booking> bookings = bookingService.getTickets(user);
            for(Booking list: bookings){
                Ticket ticket = new Ticket();
                ticket.setStart(list.getSource());
                ticket.setEnd(list.getDestination());
                ticket.setDistance(list.getDistance());
                ticket.setFare(list.getPrice());
                ticket.setDriver(driverService.getDriver(list.getDriverId()).getDriverName());
                ticket.setTrip_date(list.getPickDate());
                ticket.setTime(list.getPickTime());
                ticket.setStatus(list.getPaymentStatus().toString());
                tickets.add(ticket);
            }
            return new ResponseEntity<>(tickets,HttpStatus.OK);
        }catch(Exception ex) {
            return new ResponseEntity<>("error in server",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/payment")
    ResponseEntity<?> payTickets(@RequestBody Payment payment, HttpServletRequest httpServletRequest){
        try {
            String userId = driverService.loadByUsername(httpServletRequest);
            payment.setUserId(userId);
            payment.setPaymentId(UUID.randomUUID());
            payment.setPaymentStatus(PaymentStatus.SUCCESS);
            boolean status = paymentService.paymentPost(payment);
            Booking booking = bookingService.getBooking(UUID.fromString(payment.getBookingId()));
            booking.setPaymentStatus(PaymentStatus.SUCCESS);
            bookingService.bookTicket(booking);
            return new ResponseEntity<>("payment succesfully",HttpStatus.OK);
        }catch(Exception ex) {
            Booking booking = bookingService.getBooking(UUID.fromString(payment.getBookingId()));
            booking.setPaymentStatus(PaymentStatus.FAILED);
            payment.setPaymentStatus(PaymentStatus.FAILED);
            boolean status = paymentService.paymentPost(payment);
            bookingService.bookTicket(booking);
            return new ResponseEntity<>("error in server",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
