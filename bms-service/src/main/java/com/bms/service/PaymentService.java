package com.bms.service;

import com.bms.model.Seat;
import com.bms.model.SeatStatus;
import com.bms.model.Show;
import com.bms.model.Ticket;
import com.bms.provider.DataProvider;

import java.util.*;
import java.util.stream.Collectors;

public class PaymentService {

    public Map<Show,List<Seat>> showSeatMap;
    private final DataProvider provider;
    private final UserService userService;
    private final ShowService showService;

    public PaymentService(UserService userService, DataProvider provider, ShowService showService) {
        this.userService = userService;
        this.provider = provider;
        this.showService = showService;
        this.showSeatMap = this.provider.showSeatMap;
    }

    synchronized public Ticket bookTickets(int theatreId, int showId, int userId, List<Integer> seatNos) throws Exception {
        Show showObj = showService.getShowById(showId);
        if(!showSeatMap.containsKey(showObj)) {
            throw new Exception("Show not found");
        }
        List<Seat> seats = showSeatMap.get(showObj);
        List<Integer> indexes = new ArrayList<>();
        for(int i=0;i<seats.size();i++) {
            Seat seat = seats.get(i);
            for(int j=0;j<seatNos.size();j++) {
                if(seat.getSeatNo() == seatNos.get(j) && seat.getSeatStatus() == SeatStatus.AVAILABLE){
                    indexes.add(i);
                    seats.get(i).setSeatStatus(SeatStatus.BOOKED);
                    break;
                }
            }
        }
        if(indexes.size()<seatNos.size()) {
            throw new Exception("Selected Seats not available");
        }
//        showSeatMap.put(showObj,seats);
        return new Ticket(theatreId, showId, userService.getUserWithId(userId), seatNos);
    }
}
