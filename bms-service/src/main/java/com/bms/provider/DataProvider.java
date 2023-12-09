package com.bms.provider;

import com.bms.model.*;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor
public class DataProvider {

    public List<City> cityList;
    public Map<City,List<Theatre>> cityTheatreMap;
    public Map<Theatre,List<Movie>> theatreMovieMap;
    public Map<Movie,List<Show>> movieShowMap;
    public Map<Show,List<Seat>> showSeatMap;
    public Set<User> userSet;

}
