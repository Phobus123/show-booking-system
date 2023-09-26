package org.example.classes;

import org.example.utils.LocalDateTimeProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TicketTest {

    Show showMock;
    Ticket ticketMock;
    ArrayList<Seat> seatsMock;
    LocalDateTimeProvider localDateTimeProviderMock;

    @BeforeEach
    void setUp() {
        showMock = new Show("Mock Show", 1, 10, 2);
        String[] mockSeatNumbers = new String[1];
        mockSeatNumbers[0] = "A1";

        seatsMock = new ArrayList<>();
        seatsMock.add(new Seat("A1"));

        localDateTimeProviderMock = mock(LocalDateTimeProvider.class);
        ticketMock = new Ticket("1234", showMock, seatsMock, localDateTimeProviderMock);
    }

    @Test
    @DisplayName("Should return true if current time is within cancellation window")
    void isCancellable() {
        when(localDateTimeProviderMock.getLocalDateTime()).thenReturn(LocalDateTime.of(2024,01,01,00,00,00));
        ticketMock = new Ticket("1234", showMock, seatsMock, localDateTimeProviderMock);
        assertEquals(true, ticketMock.isCancellable());
    }

    @Test
    @DisplayName("Should return false if current time is beyond cancellation window")
    void isNotCancellable() {
        when(localDateTimeProviderMock.getLocalDateTime()).thenReturn(LocalDateTime.of(2022,01,01,00,00,00));
        ticketMock = new Ticket("1234", showMock, seatsMock, localDateTimeProviderMock);
        assertEquals(false, ticketMock.isCancellable());
    }
}