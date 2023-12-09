package com.lld.service;

import com.lld.model.Screen;
import com.lld.model.Seat;
import com.lld.model.Theatre;

import javax.net.ssl.SSLSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TheatreService {
    private Map<String, Theatre> theatres;
    private Map<String, Screen> screens;
    private Map<String, Seat> seats;

    public TheatreService() {
        this.theatres = new HashMap<>();
        this.screens = new HashMap<>();
        this.seats = new HashMap<>();
    }

    public Seat getSeat(String seatId){
        if(!seats.containsKey(seatId)) {
            System.out.print("Seat not found");
        }
        return seats.get(seatId);
    }

    public Theatre getTheatre(String theatreId) {
        if (!theatres.containsKey(theatreId)) {
            System.out.print("Theatre not found");
        }
        return theatres.get(theatreId);
    }

    public Screen getScreen(String screenId) {
        if (!screens.containsKey(screenId)) {
            System.out.print("screen not found");
        }
        return screens.get(screenId);
    }

    public Screen createScreenInTheatre(String screenName, Theatre theatre) {
        Screen screen = createScreen(screenName, theatre);
        theatre.addScreen(screen);
        return screen;
    }

    private Screen createScreen(String screenName, Theatre theatre) {
        String screenId = UUID.randomUUID().toString();
        Screen screen = new Screen(screenId,screenName,theatre);
        screens.put(screenId,screen);
        return screen;
    }

    public Theatre createTheatre(String theatreName) {
        String theatreId = UUID.randomUUID().toString();
        Theatre theatre = new Theatre(theatreId,theatreName);
        theatres.put(theatreId,theatre);
        return theatre;
    }

    public Seat createSeatInScreen(int rowNo, int seatNo, Screen screen) {
        String seatId = UUID.randomUUID().toString();
        Seat seat = new Seat(seatId,rowNo,seatNo);
        seats.put(seatId,seat);
        screen.addSeat(seat);
        return seat;
    }

}
