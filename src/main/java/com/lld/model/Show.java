package com.lld.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class Show {
    String id;
    Movie movie;
    Screen screen;
    Date startTime;
    Integer durationInSeconds;
}
