package com.parkinglot;

import enums.SpotType;
import manager.ParkingLotSystem;
import model.*;
import model.DisplayBoard;
import service.EntryGate;
import service.ExitGate;
import service.PaymentService;
import strategy.fee.HourlyFeeStrategy;
import strategy.fee.VehicleBasedFeeStrategy;
import strategy.parking.BestFitStrategy;
import strategy.parking.NearestSpotStrategy;
import strategy.parking.SmartEVStrategy;

import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        ParkingLotSystem system =
                ParkingLotSystem.getInstance();

        setupParkingLot(system);

        DisplayBoard displayBoard =
                new DisplayBoard(system.getFloors());

        boolean running = true;

        while (running) {

            printMenu();

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    parkVehicle(system);
                    break;

                case 2:
                    unparkVehicle(system);
                    break;

                case 3:
                    displayBoard.showAvailableSpots();
                    break;

                case 4:
                    showActiveTickets(system);
                    break;

                case 5:
                    changeParkingStrategy(system);
                    break;

                case 6:
                    changeFeeStrategy(system);
                    break;

                case 7:
                    showFloorAvailability(system);
                    break;

                case 8:
                    showGateInformation();
                    break;

                case 9:
                    running = false;
                    System.out.println("\nExiting System...");
                    break;

                default:
                    System.out.println("\nInvalid Choice");
            }
        }

        sc.close();
    }

    // ================= MENU =================

    private static void printMenu() {

        System.out.println("\n========== PARKING LOT SYSTEM ==========");

        System.out.println("1. Park Vehicle");
        System.out.println("2. Unpark Vehicle");
        System.out.println("3. Show Available Spots");
        System.out.println("4. Show Active Tickets");
        System.out.println("5. Change Parking Strategy");
        System.out.println("6. Change Fee Strategy");
        System.out.println("7. Show Floor Availability");
        System.out.println("8. Show Gate Information");
        System.out.println("9. Exit");

        System.out.print("\nEnter Choice : ");
    }

    // ================= SETUP =================

    private static void setupParkingLot(
            ParkingLotSystem system) {

        ParkingFloor floor1 =
                new ParkingFloor(1);

        floor1.addSpot(
                new ParkingSpot("S1", SpotType.SMALL));

        floor1.addSpot(
                new ParkingSpot("M1", SpotType.MEDIUM));

        floor1.addSpot(
                new ParkingSpot("L1", SpotType.LARGE));

        floor1.addSpot(
                new ParkingSpot("EV1", SpotType.EV));

        ParkingFloor floor2 =
                new ParkingFloor(2);

        floor2.addSpot(
                new ParkingSpot("S2", SpotType.SMALL));

        floor2.addSpot(
                new ParkingSpot("M2", SpotType.MEDIUM));

        floor2.addSpot(
                new ParkingSpot("L2", SpotType.LARGE));

        floor2.addSpot(
                new ParkingSpot("EV2", SpotType.EV));

        system.addFloor(floor1);
        system.addFloor(floor2);

        system.setParkingStrategy(
                new BestFitStrategy());

        system.setFeeStrategy(
                new VehicleBasedFeeStrategy());

        EntryGate entryGate =
                new EntryGate("ENTRY-1");

        ExitGate exitGate =
                new ExitGate(
                        "EXIT-1",
                        new PaymentService());

        system.addEntryGate(entryGate);
        system.addExitGate(exitGate);
    }

    // ================= PARK VEHICLE =================

    private static void parkVehicle(
            ParkingLotSystem system) {

        System.out.println("\nSelect Vehicle Type");

        System.out.println("1. Bike");
        System.out.println("2. Car");
        System.out.println("3. Truck");
        System.out.println("4. Electric Car");

        System.out.print("Enter Choice : ");

        int vehicleChoice = sc.nextInt();

        System.out.print("Enter License Number : ");

        String license = sc.next();

        Vehicle vehicle = createVehicle(
                vehicleChoice,
                license);

        if (vehicle == null) {

            System.out.println("Invalid Vehicle Type");
            return;
        }

        try {

            ParkingTicket ticket =
                    system.parkVehicle(vehicle);

            System.out.println(
                    "\nVehicle Parked Successfully");

            System.out.println(
                    "Ticket ID : "
                            + ticket.getTicketId());

            System.out.println(
                    "Spot Allocated : "
                            + ticket.getParkingSpot()
                            .getSpotId());

        } catch (Exception e) {

            System.out.println(
                    e.getMessage());
        }
    }

    // ================= CREATE VEHICLE =================

    private static Vehicle createVehicle(
            int choice,
            String license) {

        switch (choice) {

            case 1:
                return new Bike(license);

            case 2:
                return new Car(license);

            case 3:
                return new Truck(license);

            case 4:
                return new ElectricCar(license);

            default:
                return null;
        }
    }

    // ================= UNPARK =================

    private static void unparkVehicle(
            ParkingLotSystem system) {

        System.out.print(
                "\nEnter Ticket ID : ");

        String ticketId = sc.next();

        try {

            ParkingTicket ticket =
                    system.getActiveTickets()
                            .get(ticketId);

            double amount =
                    system.unparkVehicle(ticketId);

            long duration =
                    ticket.calculateDuration();

            System.out.println(
                    "\nParking Duration : "
                            + duration
                            + " ms");

            System.out.println(
                    "Total Fee : Rs."
                            + amount);

        } catch (Exception e) {

            System.out.println(
                    e.getMessage());
        }
    }

    // ================= ACTIVE TICKETS =================

    private static void showActiveTickets(
            ParkingLotSystem system) {

        System.out.println(
                "\n===== ACTIVE TICKETS =====");

        if (system.getActiveTickets().isEmpty()) {

            System.out.println(
                    "No Active Tickets");

            return;
        }

        for (ParkingTicket ticket :
                system.getActiveTickets().values()) {

            System.out.println(
                    "Ticket ID : "
                            + ticket.getTicketId());

            System.out.println(
                    "Vehicle : "
                            + ticket.getVehicle()
                            .getLicenseNumber());

            System.out.println(
                    "Spot : "
                            + ticket.getParkingSpot()
                            .getSpotId());

            System.out.println("----------------------");
        }
    }

    // ================= STRATEGY CHANGE =================

    private static void changeParkingStrategy(
            ParkingLotSystem system) {

        System.out.println(
                "\nSelect Parking Strategy");

        System.out.println("1. Best Fit");
        System.out.println("2. Nearest Spot");
        System.out.println("3. Smart EV");

        int choice = sc.nextInt();

        switch (choice) {

            case 1:
                system.setParkingStrategy(
                        new BestFitStrategy());
                break;

            case 2:
                system.setParkingStrategy(
                        new NearestSpotStrategy());
                break;

            case 3:
                system.setParkingStrategy(
                        new SmartEVStrategy());
                break;

            default:
                System.out.println(
                        "Invalid Choice");
                return;
        }

        System.out.println(
                "Parking Strategy Updated");
    }

    // ================= FEE STRATEGY =================

    private static void changeFeeStrategy(
            ParkingLotSystem system) {

        System.out.println(
                "\nSelect Fee Strategy");

        System.out.println(
                "1. Vehicle Based");

        System.out.println(
                "2. Hourly Based");

        int choice = sc.nextInt();

        switch (choice) {

            case 1:
                system.setFeeStrategy(
                        new VehicleBasedFeeStrategy());
                break;

            case 2:
                system.setFeeStrategy(
                        new HourlyFeeStrategy());
                break;

            default:
                System.out.println(
                        "Invalid Choice");
                return;
        }

        System.out.println(
                "Fee Strategy Updated");
    }

    // ================= FLOOR AVAILABILITY =================

    private static void showFloorAvailability(
            ParkingLotSystem system) {

        for (ParkingFloor floor :
                system.getFloors()) {

            floor.displayAvailability();
        }
    }

    // ================= GATE INFO =================

    private static void showGateInformation() {

        System.out.println(
                "\nEntry Gate : ENTRY-1");

        System.out.println(
                "Exit Gate : EXIT-1");
    }
}