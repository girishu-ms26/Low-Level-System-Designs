package com.lld.service;

import com.lld.model.Seat;
import com.lld.model.Show;
import com.lld.providers.SeatLockProvider;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class SeatAvailabilityService {
    private final BookingService bookingService;
    private final SeatLockProvider seatLockProvider;

    public List<Seat> getAvailableSeats(Show show) {
       List<Seat> allSeats = show.getScreen().getSeats();
       List<Seat> unavailableSeats = getUnavailableSeats(show);
       List<Seat> availableSeats = new ArrayList<>(allSeats);
       availableSeats.removeAll(unavailableSeats);
       return availableSeats;
    }

    private List<Seat> getUnavailableSeats(Show show) {
        List<Seat> unavailableSeats = bookingService.getBookedSeats(show);
        unavailableSeats.addAll(seatLockProvider.getLockedSeats(show));
        return unavailableSeats;
    }
}
