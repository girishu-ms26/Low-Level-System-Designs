package com.bms.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Seat {
    int seatNo;
    SeatStatus seatStatus;
}
