package com.bms.service;

import com.bms.model.City;
import com.bms.model.Movie;
import com.bms.model.Show;
import com.bms.model.Theatre;
import com.bms.provider.DataProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieService {

    private final TheatreService theatreService;
    private final CityService cityService;
    private final DataProvider provider;
    public Map<Theatre,List<Movie>> theatreMovieMap;

    public MovieService(TheatreService theatreService, CityService cityService, DataProvider provider) {
        this.theatreService = theatreService;
        this.cityService = cityService;
        this.provider = provider;
        this.theatreMovieMap = this.provider.theatreMovieMap;
    }
    public List<Movie> getAllMoviesBasedOnCity(String city) {
        City cityObject = cityService.getCityByName(city);
        List<Movie> moviesList = new ArrayList<>();
        for(int i=0;i<cityObject.getTheatreList().size();i++) {
            moviesList.addAll(cityObject.getTheatreList().get(i).getMovies());
        }
        return moviesList;
    }

    public List<Show> getAllShowBasedOnCityAndMovie(String city,String movie) {
        List<Theatre> theatres = theatreService.theatresBasedOnCity(city);
        List<Show> shows = new ArrayList<>();
        List<Movie> movies = new ArrayList<>();
        for(int i=0;i<theatres.size();i++) {
            if(theatres.get(i).getCityName() == city) {
                movies.addAll(theatres.get(i).getMovies());
            }
        }
        for(int i=0;i<movies.size();i++) {
            shows.addAll(movies.get(i).getShow());
        }
        return shows;
    }
}
