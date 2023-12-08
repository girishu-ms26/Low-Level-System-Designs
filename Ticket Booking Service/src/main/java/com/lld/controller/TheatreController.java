package com.lld.controller;

import com.lld.service.TheatreService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TheatreController {
    private final TheatreService theatreService;

    public String createTheatre(String theatreName) {
        return theatreService.createTheatre(theatreName).getId();
    }

    public String createScreenInTheatre(String screenName, String theatreId) {
        return theatreService.createScreenInTheatre(screenName,theatreService.getTheatre(theatreId)).getId();
    }

    public String createSeatInScreen(int rowNo, int seatNo, String screenId) {
        return theatreService.createSeatInScreen(rowNo, seatNo,theatreService.getScreen(screenId)).getId();
    }

}
