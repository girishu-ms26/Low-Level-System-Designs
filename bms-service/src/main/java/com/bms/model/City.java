package com.bms.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class City {
    String cityName;
    List<Theatre> theatreList;
}
