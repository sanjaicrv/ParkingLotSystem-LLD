package model;

import enums.VehicleType;


public class Truck extends Vehicle {

    public Truck(String licenseNumber) {
        super(licenseNumber, VehicleType.TRUCK);
    }
}
