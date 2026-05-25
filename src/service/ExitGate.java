package service;

import model.*;
import strategy.fee.*;
public class ExitGate {

    private String gateId;
    private PaymentService paymentService;

    // Constructor
    public ExitGate(String gateId,
                    PaymentService paymentService) {

        this.gateId = gateId;
        this.paymentService = paymentService;
    }

    // Process Vehicle Exit
    public double processPayment(
            ParkingTicket ticket,
            FeeStrategy feeStrategy) {

        // Close Ticket
        ticket.closeTicket();

        // Calculate Parking Fee
        double amount =
                feeStrategy.calculateFee(ticket);

        System.out.println(
                "Parking Fee : " + amount
        );

        // Make Payment
        boolean paymentStatus =
                paymentService.pay(amount);

        if (paymentStatus) {

            // Free Parking Spot
            ticket.getParkingSpot()
                    .removeVehicle();

            System.out.println(
                    "Vehicle Exited Successfully"
            );

            System.out.println(
                    "Exit Gate : " + gateId
            );
        }

        return amount;
    }

    // Getter
    public String getGateId() {
        return gateId;
    }
}