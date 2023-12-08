package com.lld.controller;

import com.lld.service.MovieService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MovieController {
    private final MovieService movieService;

    public String createMovie(String movieName) {
        return movieService.createMovie(movieName).getId();
    }
}
