package com.bms.controller;

import com.bms.model.*;
import com.bms.service.CityService;
import com.bms.service.MovieService;
import com.bms.service.PaymentService;
import com.bms.service.TheatreService;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class BookMyShowController {
    private PaymentService paymentService;
    private MovieService movieService;
    private CityService cityService;

    public List<City> getAllCities() {
        return cityService.getAllCities();
    }

    public List<Movie> getAllMoviesBasedOnCity(String city) {
        return movieService.getAllMoviesBasedOnCity(city);
    }

    public List<Show> getAllShowsForCityAndMovie(String city,String movie) {
        return movieService.getAllShowBasedOnCityAndMovie(city,movie);
    }

    public Ticket bookTickets(int theatreId, int showId, int userId, List<Integer> seatNos) throws Exception {
        return paymentService.bookTickets(theatreId, showId, userId, seatNos);
    }
}
