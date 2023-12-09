package com.bms;

import com.bms.controller.BookMyShowController;
import com.bms.model.*;
import com.bms.provider.DataProvider;
import com.bms.service.*;

import java.time.LocalDateTime;
import java.util.*;

public class BaseTest {

    public static BookMyShowController bookMyShowController;

    public static void setUpTestEnv() {
        List<Seat> seatsForBangaloreTheatre1Movie1Show1 = new ArrayList<>();
        for(int i=0;i<5;i++) {
            seatsForBangaloreTheatre1Movie1Show1.add(new Seat(i,SeatStatus.AVAILABLE));
        }

        List<Show> showListForBangaloreTheatre1Movie1Show1 = new ArrayList<>();
        Show show = new Show(1,1,"Animal",
                LocalDateTime.now(),LocalDateTime.now(),seatsForBangaloreTheatre1Movie1Show1);
        showListForBangaloreTheatre1Movie1Show1.add(show);

        List<Movie> movieListForBangaloreTheatre1Movie1 = new ArrayList<>();
        Movie movie = new Movie("Animal",180,1,showListForBangaloreTheatre1Movie1Show1);
        movieListForBangaloreTheatre1Movie1.add(movie);

        List<Theatre> theatreListForBangalore = new ArrayList<>();
        Theatre theatre = new Theatre("Bangalore",1,movieListForBangaloreTheatre1Movie1);
        theatreListForBangalore.add(theatre);

        List<City> cityList = new ArrayList<>();
        City city = new City("Bangalore",theatreListForBangalore);
        cityList.add(city);

        Map<City,List<Theatre>> cityTheatreMap = new HashMap<>();
        cityTheatreMap.put(city,theatreListForBangalore);
        Map<Theatre,List<Movie>> theatreMovieMap = new HashMap<>();
        theatreMovieMap.put(theatre,movieListForBangaloreTheatre1Movie1);
        Map<Movie,List<Show>> movieShowMap = new HashMap<>();
        movieShowMap.put(movie,showListForBangaloreTheatre1Movie1Show1);
        Map<Show,List<Seat>> showSeatMap = new HashMap<>();
        showSeatMap.put(show,seatsForBangaloreTheatre1Movie1Show1);

        Set<User> users = new HashSet<>();
        users.add(new User(1,"Girishu"));
        users.add((new User(2,"Interviewer")));
        DataProvider dataProvider = new DataProvider(cityList,cityTheatreMap,theatreMovieMap,movieShowMap,showSeatMap,users);
        CityService cityService = new CityService(dataProvider);
        TheatreService theatreService = new TheatreService(dataProvider,cityService);
        UserService userService = new UserService(dataProvider);
        ShowService showService = new ShowService(dataProvider);
        PaymentService paymentService = new PaymentService(userService,dataProvider,showService);

        MovieService movieService = new MovieService(theatreService,cityService,dataProvider);
        bookMyShowController = new BookMyShowController(paymentService,movieService,cityService);
    }
}
