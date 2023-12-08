package com.lld.service;

import com.lld.model.Booking;
import com.lld.model.Seat;
import com.lld.model.SeatLock;
import com.lld.model.Show;
import com.lld.providers.SeatLockProvider;

import java.util.*;
import java.util.stream.Collectors;

public class BookingService {
    private final Map<String,Booking> showBookings;
    private final SeatLockProvider seatLockProvider;

    public BookingService(SeatLockProvider seatLockProvider) {
        this.seatLockProvider = seatLockProvider;
        this.showBookings = new HashMap<>();
    }

    public Booking createBooking(String userId, Show show, List<Seat> seats) throws Exception {
        if(isAnySeatAlreadyBooked(show,seats)){
            throw new Exception("Seats not available");
        }
        seatLockProvider.lockSeats(show,seats,userId);
        String bookingId = UUID.randomUUID().toString();
        Booking booking = new Booking(bookingId,show,seats,userId);
        showBookings.put(bookingId,booking);
        return booking;
    }

    private boolean isAnySeatAlreadyBooked(Show show, List<Seat> seats) {
        final List<Seat> bookedSeats = getBookedSeats(show);
        for (Seat seat : seats) {
            if (bookedSeats.contains(seat)) {
                return true;
            }
        }
        return false;
    }

    public Booking getBooking(String bookingId) {
        if (!showBookings.containsKey(bookingId)) {
           System.out.print("Booking not available");
        }
        return showBookings.get(bookingId);
    }

    public List<Seat> getBookedSeats(Show show) {
       List<Booking> response = new ArrayList<>();
       for(Booking booking : showBookings.values()) {
           if(booking.getShow().equals(show)) {
               response.add(booking);
           }
       }
       return response.stream()
               .filter(Booking::isConfirmed)
               .map(Booking::getSeatsBooked)
               .flatMap(Collection::stream)
               .collect(Collectors.toList());
    }

    public void confirmBooking(Booking booking, String user) throws Exception {
        if (!booking.getUser().equals(user)) {
            throw new Exception("Invalid user");
        }

        for (Seat seat : booking.getSeatsBooked()) {
            if (!seatLockProvider.validateLock(booking.getShow(), seat, user)) {
                throw new Exception("Bad Request");
            }
        }
        booking.confirmBooking();
    }

}
