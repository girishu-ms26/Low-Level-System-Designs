package com.lld.controller;

import com.lld.service.BookingService;
import com.lld.service.PaymentsService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PaymentsController {
    private final PaymentsService paymentService;
    private final BookingService bookingService;

    public void paymentFailed(String bookingId, String user) {
        paymentService.processPaymentFailed(bookingService.getBooking(bookingId),user);
    }

    public void paymentSuccess(String bookingId,String user) throws Exception {
        bookingService.confirmBooking(bookingService.getBooking(bookingId), user);
    }

}
