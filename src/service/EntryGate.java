package service;


import model.*;

import java.util.UUID;

public class EntryGate {

    private String gateId;

    // Constructor
    public EntryGate(String gateId) {
        this.gateId = gateId;
    }

    // Generate Parking Ticket
    public ParkingTicket generateTicket(
            Vehicle vehicle,
            ParkingSpot parkingSpot) {

        // Assign Vehicle to Spot
        parkingSpot.assignVehicle(vehicle);

        // Generate Unique Ticket ID
        String ticketId =
                UUID.randomUUID().toString();

        // Create Parking Ticket
        ParkingTicket ticket =
                new ParkingTicket(
                        ticketId,
                        vehicle,
                        parkingSpot
                );

        System.out.println("Ticket Generated Successfully");

        System.out.println("Gate ID : " + gateId);

        System.out.println("Ticket ID : " + ticketId);

        return ticket;
    }

    // Getter
    public String getGateId() {
        return gateId;
    }
}