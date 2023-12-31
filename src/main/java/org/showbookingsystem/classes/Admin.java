package org.showbookingsystem.classes;

import org.showbookingsystem.Main;

import java.util.ArrayList;

public class Admin extends User {

    private static Admin single_instance = null;

    private Admin() {}

    public static synchronized Admin getInstance()
    {
        if (single_instance == null)
            single_instance = new Admin();

        return single_instance;
    }

    public void setupShow(String showNumber, int numberOfRows, int numberOfSeatsPerRow, int cancellationWindow) {
        Show show = new Show(showNumber, numberOfRows, numberOfSeatsPerRow, cancellationWindow);
        Main.addShow(show);
    }

    public void viewShow(String showNumber) {
        ArrayList<Ticket> tickets = Main.getTicketsForShow(showNumber);
        if (tickets == null) {
            System.out.println("No tickets for this show");
            return;
        }

        tickets.forEach(ticket -> {
            System.out.printf("Show Number: %s, Ticket Number: %s, Buyer Phone Number: %s, Seat Numbers: %s", ticket.getShow().getShowNumber(), ticket.getId(), ticket.getPhoneNumber(), ticket.getSeatsString());
            System.out.println();
        });
    }
}
