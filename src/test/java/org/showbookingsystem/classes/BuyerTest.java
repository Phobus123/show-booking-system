package org.showbookingsystem.classes;

import org.showbookingsystem.classes.Buyer;
import org.showbookingsystem.classes.Seat;
import org.showbookingsystem.classes.Show;
import org.showbookingsystem.classes.Ticket;
import org.showbookingsystem.utils.LocalDateTimeProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class BuyerTest {

    Show showMock;
    Buyer buyerMock;
    Ticket ticketMock;
    ArrayList<Seat> seatsMock;


    @BeforeEach
    void setUp() {
        buyerMock = Mockito.spy(Buyer.class);
        when(buyerMock.getTicket(anyString())).thenReturn(ticketMock);

        showMock = new Show("Mock Show", 1, 10, 2);
        String[] mockSeatNumbers = new String[1];
        mockSeatNumbers[0] = "A1";

        seatsMock = new ArrayList<>();
        seatsMock.add(new Seat("A1"));

        ticketMock = new Ticket("1234", showMock, seatsMock, new LocalDateTimeProvider());
    }

    @Test
    @DisplayName("Throw error if ticketId does not exist")
    void cancelInvalidTicket() {
        Buyer buyerMock = Mockito.spy(Buyer.class);
        assertThrows(IllegalArgumentException.class, () -> buyerMock.getInstance().cancel("Mock ticket id", "123"));
    }

    @Test
    @DisplayName("Throw error if phoneNumber provided to cancel ticket does not match")
    void cancelTicketWithWrongPhoneNumber() {
        when(buyerMock.getTicket(anyString())).thenReturn(ticketMock);
        assertThrows(IllegalArgumentException.class, () -> buyerMock.getInstance().cancel("Mock ticket id", "123"));
    }
}