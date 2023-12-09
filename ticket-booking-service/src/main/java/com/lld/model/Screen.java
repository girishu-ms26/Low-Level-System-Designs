package com.lld.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Screen {
    String id;
    String name;
    Theatre theatre;
    List<Seat> seats;

    public Screen(String id, String name, Theatre theatre) {
        this.id = id;
        this.name = name;
        this.theatre = theatre;
        this.seats = new ArrayList<>();
    }

    public void addSeat(Seat seat) {
        seats.add(seat);
    }
}
