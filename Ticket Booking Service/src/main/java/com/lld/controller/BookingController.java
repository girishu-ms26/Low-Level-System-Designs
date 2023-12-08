package com.lld.controller;

import com.lld.model.Seat;
import com.lld.model.Show;
import com.lld.service.BookingService;
import com.lld.service.ShowService;
import com.lld.service.TheatreService;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class BookingController {
    private final ShowService showService;
    private final TheatreService theatreService;
    private final BookingService bookingService;

    public String createBooking(String userId, String showId, List<String> seatIds) throws Exception {
        Show show = showService.getShow(showId);
        List<Seat> seats = seatIds.stream().map(theatreService::getSeat).collect(Collectors.toList());
        return bookingService.createBooking(userId, show, seats).getId();
    }
}
