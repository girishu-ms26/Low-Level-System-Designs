package com.bms.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor
@Getter
public class Show {
    int showId;
    int theatreId;
    String movieName;
    LocalDateTime startTime;
    LocalDateTime endTime;
    List<Seat> seats;
}
