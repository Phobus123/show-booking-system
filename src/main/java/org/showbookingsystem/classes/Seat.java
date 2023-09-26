package org.showbookingsystem.classes;

public class Seat {
    private boolean isAvailable = true;
    private String seatNumber;

    public Seat(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
