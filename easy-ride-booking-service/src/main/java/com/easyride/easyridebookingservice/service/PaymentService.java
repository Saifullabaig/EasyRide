package com.easyride.easyridebookingservice.service;

import com.easyride.easyridebookingservice.model.Payment;
import com.easyride.easyridebookingservice.repo.PayRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PaymentService {

    @Autowired
    private PayRepo payRepo;


    public boolean paymentPost(Payment payment){
        try{
            payRepo.save(payment);
            return true;
        }catch (Exception ex){
            System.out.println("error"+ex.getMessage());
            return false;
        }
    }

    public ArrayList<Payment> getPayments(){
        return (ArrayList<Payment>) payRepo.findAll();
    }


}

