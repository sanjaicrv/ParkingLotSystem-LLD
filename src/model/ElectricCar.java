package model;

import enums.VehicleType;


public class ElectricCar extends Vehicle {

    public ElectricCar(String licenseNumber) {
        super(licenseNumber, VehicleType.ELECTRIC);
    }
}
