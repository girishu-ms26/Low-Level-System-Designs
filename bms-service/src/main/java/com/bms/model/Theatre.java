package com.bms.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Theatre {
    int theatreId;
    String city;
    Movie movie;
}
