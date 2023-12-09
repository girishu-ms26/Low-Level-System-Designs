package com.bms.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Theatre {
    String cityName;
    int theatreId;
    List<Movie> movies;
}
