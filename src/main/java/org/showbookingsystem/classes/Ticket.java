package org.showbookingsystem.classes;

import org.showbookingsystem.Main;
import org.showbookingsystem.utils.LocalDateTimeProvider;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class Ticket {
    private String phoneNumber;
    private LocalDateTime createdAt;
    private Show show;
    private ArrayList<Seat> seats;
    private String id;
    private boolean isCancelled = false;

    public Ticket(String phoneNumber, Show show, ArrayList<Seat> seats, LocalDateTimeProvider localDateTimeProvider) {
        this.phoneNumber = phoneNumber;
        this.show = show;
        this.id = UUID.randomUUID().toString();
        this.createdAt = localDateTimeProvider.getLocalDateTime();
        System.out.println("createdAt " + createdAt);
        this.seats = seats;
        Main.addTicket(this);
    }

    public boolean isCancellable() {
        System.out.println(LocalDateTime.now());
        System.out.println(createdAt);
        return LocalDateTime.now().compareTo(createdAt.plusMinutes(show.getCancellationWindow())) < 0;
    }

    public void cancel() {
        this.show.releaseSeats(seats);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Show getShow() {
        return show;
    }

    public String getId() {
        return id;
    }

    public boolean isCancelled() {
        return isCancelled;
    }
}
