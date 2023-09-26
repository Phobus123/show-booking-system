package org.showbookingsystem.classes;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.showbookingsystem.classes.Seat;
import org.showbookingsystem.classes.Show;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ShowTest {

    Show show;

    @BeforeEach
    void setUp() {
        show = new Show("Mock Show", 1, 5, 2);
    }

    @Test
    @DisplayName("Return availability of seats for show")
    void testGetAvailableSeats() {
        assertEquals(true, show.getAvailableSeats().contains("A1, A2, A3, A4, A5"));
    }

    @Test
    @DisplayName("Should release seats for a show")
    void releaseSeats() {
        ArrayList<Seat> seats = new ArrayList<>();
        Seat mockSeat1 = new Seat("A1");
        mockSeat1.setAvailable(false);
        seats.add(mockSeat1);
        seats.add(new Seat("A2"));
        seats.add(new Seat("A3"));
        show.releaseSeats(seats);
        for (Seat seat : seats) {
            assertEquals(true, seat.isAvailable());
        }
    }

    @AfterEach
    void tearDown() {
    }
}