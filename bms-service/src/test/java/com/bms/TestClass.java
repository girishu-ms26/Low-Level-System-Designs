package com.bms;

import com.bms.model.*;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TestClass extends BaseTest {

    @BeforeAll
    public static void setup() {
        setUpTestEnv();
    }

    @Test
    // Booking should be successful
    public void scenarioOne() throws Exception {
        int userId = 1;
        List<City> cities = bookMyShowController.getAllCities();
        System.out.println("List of Cities");
        for (City city : cities) {
            System.out.println(city.getCityName());
        }
        String city = "Bangalore";

        List<Movie> movies = bookMyShowController.getAllMoviesBasedOnCity(city);
        System.out.println("Movies Based on city");
        for (int i=0;i<movies.size();i++) {
            System.out.println("Movie Name " +movies.get(i).getMovieName()+
                    " Theatre Id "+movies.get(i).getTheatreId()+
                    " Available Shows "+movies.get(i).getShow().size());
        }
        String movieName = "Animal";

        List<Show> showList = bookMyShowController.getAllShowsForCityAndMovie(city,movieName);
        for (int i=0;i<showList.size();i++) {
            System.out.println("Show Id "+ showList.get(i).getShowId()+" Movie Name "+ showList.get(i).getMovieName());
        }

        int theatreId = 1;
        int showId = 1;
        List<Integer> seats = new ArrayList<>();
        seats.add(1);
        seats.add(2);
        Ticket ticket = bookMyShowController.bookTickets(theatreId,showId,userId,seats);
        System.out.println("Ticket successfully booked");
        System.out.println("Show ID "+ticket.getShowId()+
                " theatre Id"+ticket.getTheatreId() +" UserName " +ticket.getUser().getUserName());
    }
    @Test
    //show throw Exception with message, Seats already booked
    public void scenarioTwo() throws Exception {
        int userId = 2;
        List<City> cities = bookMyShowController.getAllCities();
        System.out.println("List of Cities");
        for (City city : cities) {
            System.out.println(city.getCityName());
        }
        String city = "Bangalore";
        List<Movie> movies = bookMyShowController.getAllMoviesBasedOnCity(city);
        Assert.assertEquals(SeatStatus.BOOKED,movies.get(0).getShow().get(0).getSeats().get(1).getSeatStatus());
        System.out.println("Movies Based on city");
        for (Movie movie : movies) {
            System.out.println("Movie Name " +movie.getMovieName()+
                    " Theatre Id "+movie.getTheatreId()+
                    " Available Shows "+movie.getShow());
        }
        String movieName = "Animal";
        List<Show> showList = bookMyShowController.getAllShowsForCityAndMovie(city,movieName);
        for (int i=0;i<showList.size();i++) {
            System.out.println("Show Id "+ showList.get(i).getShowId()+" Movie Name "+ showList.get(i).getMovieName());
        }
        int theatreId = 1;
        int showId = 1;
        List<Integer> seats = new ArrayList<>();
        seats.add(1);
        seats.add(2);
        Ticket ticket = bookMyShowController.bookTickets(theatreId,showId,userId,seats);
        System.out.println("Ticket successfully booked");
        System.out.println("Show ID "+ticket.getShowId()+
                "theatre Id"+ticket.getTheatreId() +" UserName " +ticket.getUser().getUserName());
    }
}
