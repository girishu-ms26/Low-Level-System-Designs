package com.lld.service;

import com.lld.model.Booking;

import java.util.HashMap;
import java.util.Map;

public class PaymentsService {

    private Map<Booking, Integer> bookingFailures;

    public PaymentsService() {
        this.bookingFailures = new HashMap<>();
    }

    public void processPaymentFailed(Booking booking, String user) {
        if(!booking.getId().equals(user)) {
            System.out.print("Invalid user");
        }
        if(!bookingFailures.containsKey(booking.getId())) {
            bookingFailures.put(booking,0);
        }

    }
}
