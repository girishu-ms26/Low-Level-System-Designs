package com.bms.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Movie {
    String movieName;
    int durationInMinutes;
    int theatreId;
    List<Show> show;
}
