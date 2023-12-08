package com.lld.providers;

import com.google.common.collect.ImmutableList;
import com.lld.model.Seat;
import com.lld.model.SeatLock;
import com.lld.model.Show;

import java.util.*;

public class InMemorySeatLockProvider implements SeatLockProvider {

    private final Map<Show, Map<Seat, SeatLock>> locks;

    public InMemorySeatLockProvider() {
        this.locks = new HashMap<>();
    }

    @Override
    synchronized public void lockSeats(Show show, List<Seat> seats , String user) throws Exception {
        for(Seat seat : seats) {
            if(isSeatLocked(show,seat,user)) {
                throw new Exception("Seat is already locked");
            }
        }
        for(Seat seat : seats) {
            lockSeat(show,seat,user);
        }
    }

    @Override
    public void unlockSeats(Show show, List<Seat> seat, String user) {

    }

    @Override
    public boolean validateLock(Show show, Seat seat, String user) {
        return isSeatLocked(show, seat, user) && locks.get(show).get(seat).getLockedBy().equals(user);
    }

    @Override
    public List<Seat> getLockedSeats(Show show) {
       if(!locks.containsKey(show)) {
            return ImmutableList.of();
       }
       List<Seat> lockedSeats = new ArrayList<>();
       for(Seat seat : locks.get(show).keySet()) {
           if(isSeatLocked(show, seat, null)){
               lockedSeats.add(seat);
           }
       }
       return lockedSeats;
    }

    private boolean isSeatLocked(final Show show, final Seat seat, String user) {
//        return locks.containsKey(show) && locks.get(show).containsKey(seat) && !locks.get(show).get(seat).isLockExpired();
        if(locks.containsKey(show)) {
            if(locks.get(show).containsKey(seat)) {
                if(locks.get(show).get(seat).getLockedBy() == user) {
                    return true;
                }
            }
        }
        return false;
    }

    private void lockSeat(final Show show, final Seat seat, final String user) {
        if(!locks.containsKey(show)) {
            locks.put(show,new HashMap<>());
        }
        SeatLock lock = new SeatLock(show,seat,user);
        locks.get(show).put(seat,lock);
    }
}
