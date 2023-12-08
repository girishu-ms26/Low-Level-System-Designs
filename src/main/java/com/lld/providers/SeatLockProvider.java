package com.lld.providers;

import com.lld.model.Seat;
import com.lld.model.Show;

import java.util.List;

public interface SeatLockProvider {
    void lockSeats(Show show, List<Seat> seat, String user) throws Exception;
    void unlockSeats(Show show, List<Seat> seat, String user);
    boolean validateLock(Show show, Seat seat, String user);

    List<Seat> getLockedSeats(Show show);
}
