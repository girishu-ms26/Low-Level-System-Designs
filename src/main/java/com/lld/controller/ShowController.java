package com.lld.controller;

import com.lld.model.Movie;
import com.lld.model.Screen;
import com.lld.model.Seat;
import com.lld.model.Show;
import com.lld.service.MovieService;
import com.lld.service.SeatAvailabilityService;
import com.lld.service.ShowService;
import com.lld.service.TheatreService;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ShowController {
    private final TheatreService theatreService;
    private final MovieService movieService;
    private final ShowService showService;
    private final SeatAvailabilityService seatAvailabilityService;

    public String createShow(String movieId, String screenId, Date startTime, Integer durationInSeconds) {
        Screen screen = theatreService.getScreen(screenId);
        Movie movie = movieService.getMovie(movieId);
        return showService.createShow(movie, screen, startTime, durationInSeconds).getId();
    }

    public List<String> getAvailableSeats(String showId) {
        Show show = showService.getShow(showId);
        List<Seat> availableSeats = seatAvailabilityService.getAvailableSeats(show);
        return availableSeats.stream().map(Seat::getId).collect(Collectors.toList());
    }
}
