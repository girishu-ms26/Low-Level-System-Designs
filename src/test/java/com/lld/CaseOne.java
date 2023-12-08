package com.lld;

import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class CaseOne extends BaseTest {

    @Before
    public void setup(){
        setupControllers(10,0);
    }

    @Test
    public void testCaseOne() throws Exception {
        String user1 = "User1";
        String user2 = "User2";

        final String movie = movieController.createMovie("Movie 1");
        final String screen = setupScreen();
        final List<String> screen1SeatsId = createSeats(theatreController,screen, 2, 10);
        final String show = showController.createShow(movie, screen, new Date(), 2*60*60);

        List<String> availableSeats = showController.getAvailableSeats(show);

        validateSeatsList(availableSeats,screen1SeatsId, ImmutableList.of());
        ImmutableList<String> u1SelectedSeats = ImmutableList.of(
                screen1SeatsId.get(0),
                screen1SeatsId.get(2),
                screen1SeatsId.get(5),
                screen1SeatsId.get(10)
        );

        final String bookingId = bookingController.createBooking(user1,show,u1SelectedSeats);
        paymentsController.paymentSuccess(bookingId,user1);

        final List<String> u2AvailableSeats = showController.getAvailableSeats(show);
        validateSeatsList(u2AvailableSeats,screen1SeatsId,u1SelectedSeats);
    }
}
