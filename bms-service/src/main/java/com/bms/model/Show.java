package com.bms.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Getter
public class Show {
    int showId;
    Date startTime;
    Date endTime;
    Theatre theatre;
    Movie movie;
    List<Seat> seats;
}
