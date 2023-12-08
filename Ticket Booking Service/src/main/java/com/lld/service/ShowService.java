package com.lld.service;

import com.lld.model.Movie;
import com.lld.model.Screen;
import com.lld.model.Show;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ShowService {
    private final Map<String, Show> shows;

    public ShowService() {
        this.shows = new HashMap<>();
    }

    public Show getShow(String showId) {
        if(!this.shows.containsKey(showId)){
            System.out.print("Show Not Found");
        }
        return this.shows.get(showId);
    }

    public Show createShow(Movie movie, Screen screen, Date startTime, Integer durationInSeconds) {
        String showId = UUID.randomUUID().toString();
        Show show = new Show(showId,movie,screen,startTime,durationInSeconds);
        this.shows.put(showId,show);
        return show;
    }
}
