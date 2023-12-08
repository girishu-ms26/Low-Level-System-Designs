package com.lld.service;

import com.lld.model.Movie;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MovieService {

    private final Map<String, Movie> movies;

    public MovieService() {
        this.movies = new HashMap<>();
    }

    public Movie getMovie(String movieId) {
        if(!movies.containsKey(movieId)) {
            System.out.print("Movie Not Found");
        }
        return movies.get(movieId);
    }

    public Movie createMovie(String movieName) {
        String movieId = UUID.randomUUID().toString();
        Movie movie = new Movie(movieId,movieName);
        movies.put(movieId,movie);
        return movie;
    }
}
