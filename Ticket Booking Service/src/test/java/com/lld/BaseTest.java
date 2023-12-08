package com.lld;

import com.lld.controller.*;
import com.lld.providers.InMemorySeatLockProvider;
import com.lld.service.*;
import junit.framework.Assert;

import java.util.ArrayList;
import java.util.List;

public class BaseTest {

    public BookingController bookingController;
    public ShowController showController;
    public TheatreController theatreController;
    public MovieController movieController;
    public PaymentsController paymentsController;

    public void setupControllers(int lockTimeout, int allowedRetries) {
        final InMemorySeatLockProvider seatLockProvider = new InMemorySeatLockProvider();
        final BookingService bookingService = new BookingService(seatLockProvider);
        final MovieService movieService = new MovieService();
        final ShowService showService = new ShowService();
        final TheatreService theatreService = new TheatreService();
        final PaymentsService paymentsService = new PaymentsService();
        final SeatAvailabilityService seatAvailabilityService = new SeatAvailabilityService(bookingService,seatLockProvider);

        bookingController = new BookingController(showService,theatreService,bookingService);
        showController = new ShowController(theatreService,movieService,showService,seatAvailabilityService);
        theatreController = new TheatreController(theatreService);
        movieController = new MovieController(movieService);
        paymentsController = new PaymentsController(paymentsService, bookingService);
    }

    public List<String> createSeats(TheatreController theatreController, String screen, int numRows, int numSeatsInRow) {
        List<String> seats = new ArrayList<>();
        for (int row = 0; row < numRows; row++) {
            for (int seatNo = 0; seatNo < numSeatsInRow; seatNo++) {
                String seat = theatreController.createSeatInScreen(row, seatNo, screen);
                seats.add(seat);
            }
        }
        return seats;
    }

    public String setupScreen() {
        final String theatre = theatreController.createTheatre("Theatre 1");
        return theatreController.createScreenInTheatre("Screen 1", theatre);
    }

    public void validateSeatsList(List<String> seatsList, List<String> allSeatsInScreen, List<String> excludedSeats) {
        for (String includedSeat: allSeatsInScreen) {
            if (!excludedSeats.contains(includedSeat)) {
                Assert.assertTrue(seatsList.contains(includedSeat));
            }
        }

        for (String excludedSeat: excludedSeats) {
            Assert.assertFalse(seatsList.contains(excludedSeat));
        }
    }
}
