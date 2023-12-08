package com.lld.model;

import lombok.Getter;

import java.util.List;

@Getter
public class Booking {
    String id;
    Show show;
    List<Seat> seatsBooked;
    String user;
    BookingStatus bookingStatus;

    public Booking(String id, Show show, List<Seat> seatsBooked, String user) {
        this.id = id;
        this.show = show;
        this.seatsBooked = seatsBooked;
        this.user = user;
        this.bookingStatus = BookingStatus.CREATED;
    }

    public boolean isConfirmed() {
        return this.bookingStatus == BookingStatus.CONFIRMED;
    }
    public void confirmBooking() {
        if (this.bookingStatus != BookingStatus.CREATED) {
            System.out.print("Invalid State");
        }
        this.bookingStatus = BookingStatus.CONFIRMED;
    }

}
