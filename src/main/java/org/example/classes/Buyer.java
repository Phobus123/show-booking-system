package org.example.classes;

import org.example.Main;
import org.example.utils.LocalDateTimeProvider;

import java.util.ArrayList;
import java.util.Objects;
public class Buyer extends User {

    private static Buyer single_instance = null;

    private Buyer() {}

    public static synchronized Buyer getInstance()
    {
        if (single_instance == null)
            single_instance = new Buyer();

        return single_instance;
    }

    public void checkAvailability(String showNumber) {
        try {
            Show show = Main.shows.get(showNumber);
            System.out.println(show.getAvailableSeats());
        } catch (Exception e) {
            throw new IllegalArgumentException("Oops, the show you are looking for does not exist" + e.getMessage());
        }
    }

    public Ticket getTicket (String ticketId) {
        return Main.getTicketById(ticketId);
    }

    public void cancel(String ticketId, String phoneNumber) {
        Ticket ticket = this.getTicket(ticketId);

        if (ticket == null) {
            throw new IllegalArgumentException("Ticket ID: " + ticketId + " does not exist");
        }

        if (!Objects.equals(ticket.getPhoneNumber(), phoneNumber)) {
            throw new IllegalArgumentException("Phone number provided " + phoneNumber + " does not match phoneNumber of ticket, unable to cancel");
        }

        if (ticket.isCancellable()) {
            ticket.cancel();
            Main.removeTicket(ticketId);
            return;
        }

        System.out.println("Sorry, unable to cancel beyond the cancellation window of: " + ticket.getShow().getCancellationWindow());
    }

    public void book(String showNumber, String phoneNumber, String[] listOfSeatsNumbers) {
        Show show = Main.getShow(showNumber);
        ArrayList<Seat> seats = show.bookSeats(listOfSeatsNumbers);
        Ticket ticket = new Ticket(phoneNumber, show, seats, new LocalDateTimeProvider());
        System.out.println("Your ticket ID: " + ticket.getId());
    }
}
