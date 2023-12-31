package com.bms.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Ticket {
    int theatreId;
    int showId;
    User user;
    List<Integer> seatNo;

}
