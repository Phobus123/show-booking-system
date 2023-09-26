package org.example.classes;

import java.util.ArrayList;
import java.util.HashMap;

public class Show {
    private final char[] UPPER_CASE_ALPHABETS = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    private final int MAX_SEATS_PER_ROW = 10;
    private final int MAX_ROWS = 26;
    private HashMap<String, ArrayList<Seat>> rows;
    private String showNumber;
    private int cancellationWindow = 2;

    public Show(String showNumber, int numberOfRows, int seatsPerRow, int cancellationWindow) {
        if (numberOfRows > MAX_ROWS) {
            System.out.println("Maximum number of rows is 10");
            return;
        }

        if (seatsPerRow > MAX_SEATS_PER_ROW) {
            System.out.println("Maximum number of seats per row is 26");
            return;
        }

        this.rows = new HashMap<>(numberOfRows);
        this.showNumber = showNumber;
        this.cancellationWindow = cancellationWindow;

        for (int i = 0; i < numberOfRows; i++) {
            String currentRowLetter = String.valueOf(UPPER_CASE_ALPHABETS[i]);
            ArrayList<Seat> seatsForRow = new ArrayList<>(seatsPerRow);
            for (int seatCount = 0; seatCount < seatsPerRow; seatCount++) {
                seatsForRow.add(new Seat(currentRowLetter + (seatCount + 1)));
                this.rows.put(String.valueOf(UPPER_CASE_ALPHABETS[i]), seatsForRow);
            }
        }

    }

    public String getAvailableSeats() {
        final StringBuilder builder = new StringBuilder();

        this.rows.values().forEach(row -> {
            row.forEach(seat -> {
                if (seat.isAvailable()) {
                    builder.append(seat.getSeatNumber() + ", ");
                }
            });
        });

        return builder.toString();
    }

    public ArrayList<Seat> bookSeats(String[] listOfSeatNumbers) {
        ArrayList<Seat> seats = new ArrayList<>();
        for (String seatNumber : listOfSeatNumbers) {
            String[] seatNumberSplit = seatNumber.split("");
            Seat seat = this.rows.get(seatNumberSplit[0]).get(Integer.parseInt(seatNumberSplit[1]) - 1);
            System.out.println(seat.getSeatNumber());
            if (seat.isAvailable()) {
                seat.setAvailable(false);
                seats.add(seat);
            } else {
                throw new IllegalStateException("Sorry, seat taken!");
            }
        }
        return seats;
    }

    public void releaseSeats(ArrayList<Seat> seatsToRelease) {
        for (Seat seat : seatsToRelease) {
            if (!seat.isAvailable()) {
                seat.setAvailable(true);
            }
        }
    }

    public int getCancellationWindow() {
        return cancellationWindow;
    }

    public String getShowNumber() {
        return showNumber;
    }
}
