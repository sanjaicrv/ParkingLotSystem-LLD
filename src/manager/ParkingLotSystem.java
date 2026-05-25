package manager;


import model.*;
import strategy.fee.FeeStrategy;
import strategy.parking.ParkingStrategy;
import service.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLotSystem {

    // Singleton Instance
    private static ParkingLotSystem instance;

    // Strategy Objects
    private ParkingStrategy parkingStrategy;
    private FeeStrategy feeStrategy;

    // Parking Lot Components
    private List<ParkingFloor> floors;
    private List<EntryGate> entryGates;
    private List<ExitGate> exitGates;

    // Active Tickets
    private Map<String, ParkingTicket> activeTickets;

    // Private Constructor
    private ParkingLotSystem() {

        floors = new ArrayList<>();
        entryGates = new ArrayList<>();
        exitGates = new ArrayList<>();

        activeTickets = new HashMap<>();
    }

    // Singleton Method
    public static ParkingLotSystem getInstance() {

        if (instance == null) {
            instance = new ParkingLotSystem();
        }

        return instance;
    }

    // Add Floor
    public void addFloor(ParkingFloor floor) {
        floors.add(floor);
    }

    // Add Entry Gate
    public void addEntryGate(EntryGate gate) {
        entryGates.add(gate);
    }

    // Add Exit Gate
    public void addExitGate(ExitGate gate) {
        exitGates.add(gate);
    }
    //get floors
    public List<ParkingFloor> getFloors() {
        return floors;
    }
    // Park Vehicle
    public ParkingTicket parkVehicle(Vehicle vehicle) {

        // Find Parking Spot using Strategy
        ParkingSpot spot =
                parkingStrategy.findSpot(
                        floors,
                        vehicle
                );

        if (spot == null) {
            throw new RuntimeException(
                    "No Parking Spot Available"
            );
        }

        // Take First Entry Gate
        EntryGate gate = entryGates.get(0);

        // Generate Ticket
        ParkingTicket ticket =
                gate.generateTicket(
                        vehicle,
                        spot
                );

        // Store Active Ticket
        activeTickets.put(
                ticket.getTicketId(),
                ticket
        );

        return ticket;
    }

    // Unpark Vehicle
    public double unparkVehicle(String ticketId) {

        ParkingTicket ticket =
                activeTickets.get(ticketId);

        if (ticket == null) {
            throw new RuntimeException(
                    "Invalid Ticket"
            );
        }

        // Take First Exit Gate
        ExitGate gate = exitGates.get(0);

        // Process Payment
        double amount =
                gate.processPayment(
                        ticket,
                        feeStrategy
                );

        // Remove Active Ticket
        activeTickets.remove(ticketId);

        return amount;
    }

    // Set Parking Strategy
    public void setParkingStrategy(
            ParkingStrategy strategy) {

        this.parkingStrategy = strategy;
    }

    // Set Fee Strategy
    public void setFeeStrategy(
            FeeStrategy strategy) {

        this.feeStrategy = strategy;
    }
    public List<EntryGate> getEntryGates() {
        return entryGates;
    }

    public List<ExitGate> getExitGates() {
        return exitGates;
    }

    public Map<String, ParkingTicket> getActiveTickets() {
        return activeTickets;
    }
}