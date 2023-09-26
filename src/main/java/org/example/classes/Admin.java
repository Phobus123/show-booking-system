package org.example.classes;

import org.example.Main;

import java.util.ArrayList;
import java.util.Arrays;

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
        Main.shows.put(showNumber, show);
    }

    public void viewShow(String showNumber) {
        ArrayList<Ticket> tickets = Main.ticketsByShow.get(showNumber);
        if (tickets == null) {
            System.out.println("No tickets for this show");
            return;
        }

        tickets.forEach(ticket -> {
            System.out.println(ticket.getId());
        });
    }
}
