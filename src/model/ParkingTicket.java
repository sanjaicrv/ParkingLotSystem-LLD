package model;

import enums.TicketStatus;

public class ParkingTicket {

    private String ticketId;
    private long entryTime;
    private long exitTime;
    @SuppressWarnings("unused")
	private TicketStatus status;
    private Vehicle vehicle;
    private ParkingSpot parkingSpot;

    public ParkingTicket(String ticketId, Vehicle vehicle, ParkingSpot parkingSpot) {
        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
        this.entryTime = System.currentTimeMillis();
        this.status = TicketStatus.ACTIVE;
    }

    public void closeTicket() {
        this.exitTime = System.currentTimeMillis();
        this.status = TicketStatus.PAID;
    }

    public long calculateDuration() {
        long endTime = exitTime == 0 ? System.currentTimeMillis() : exitTime;
        return Math.max(1, (endTime - entryTime) / (1000 * 60 * 60));
    }

    public String getTicketId() {
        return ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }
}