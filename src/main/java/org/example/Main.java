package org.example;

import org.example.classes.Admin;
import org.example.classes.Buyer;
import org.example.classes.Show;
import org.example.classes.Ticket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
public class Main {
    // HashMap<showNumber, Show>
    public static HashMap<String, Show> shows = new HashMap<>();
    // HashMap<phoneNumber, buyer>
    public static HashMap<String, Buyer> buyers = new HashMap<>();
    // HashMap<showNumber, Ticket[]>
    public static HashMap<String, ArrayList<Ticket>> ticketsByShow = new HashMap<>();
    // HashMap <ticketId, Ticket>
    public static HashMap<String, Ticket> tickets = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("Show Booking System");
        Scanner in = new Scanner(System.in).useDelimiter("\\s");

        while (true) {
            try {
                String command = in.next();
                System.out.println(command);

                switch (command) {
                    case "Setup":
                        String showNumber = in.next();
                        int numberOfRows = Integer.parseInt(in.next());
                        int numberOfSeatsPerRow = Integer.parseInt(in.next());
                        int cancellationWindow = Integer.parseInt(in.next());
                        Admin.getInstance().setupShow(showNumber, numberOfRows, numberOfSeatsPerRow, cancellationWindow);
                        break;
                    case "View":
                        showNumber = in.next();
                        Admin.getInstance().viewShow(showNumber);
                        break;
                    case "Availability":
                        showNumber = in.next().trim();
                        Buyer.getInstance().checkAvailability(showNumber);
                        break;
                    case "Book":
                        showNumber = in.next();
                        String phoneNumber = in.next();
                        String[] listOfSeats = in.next().split(",");
                        Buyer.getInstance().book(showNumber, phoneNumber, listOfSeats);
                        break;
                    case "Cancel":
                        String ticketId = in.next();
                        phoneNumber = in.next();
                        Buyer.getInstance().cancel(ticketId, phoneNumber);
                        break;
                    case "Exit":
                        System.exit(0);
                        in.close();
                        break;
                    default:
                        System.out.println("Sorry, unknown command " + command + " entered!");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println( "Please enter a command to continue or Exit to quit the program");
            }
        }
    }

    public static Ticket getTicketById(String ticketId) {
        return tickets.get(ticketId);
    }

    public static void addTicket(Ticket ticket) {
        Show show = ticket.getShow();
        tickets.put(ticket.getId(), ticket);
        if (ticketsByShow.containsKey(show.getShowNumber())) {
            ticketsByShow.get(show.getShowNumber()).add(ticket);
        } else {
            ArrayList<Ticket> tickets = new ArrayList<>();
            tickets.add(ticket);
            ticketsByShow.put(show.getShowNumber(), tickets);
        }
    }

    public static void removeTicket(String ticketId) {
        tickets.remove(ticketId);
    }

    public static Show getShow(String showNumber) {
        if (shows.containsKey(showNumber)) {
            return shows.get(showNumber);
        }

        throw new IllegalArgumentException("Show with showNumber: " + showNumber + " does not exist");
    }
}