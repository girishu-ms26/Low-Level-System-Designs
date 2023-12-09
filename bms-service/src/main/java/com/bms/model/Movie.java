package com.bms.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Movie {
    String movieName;
    int durationInMinutes;
    Show show;
}
