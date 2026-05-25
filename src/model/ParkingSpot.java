package model;

import enums.SpotType;
import enums.VehicleType;

public class ParkingSpot {

    private String spotId;
    private SpotType spotType;
    private boolean occupied;
    private Vehicle currentVehicle;

    public ParkingSpot(String spotId, SpotType spotType) {
        this.spotId = spotId;
        this.spotType = spotType;
    }

    public boolean canFitVehicle(Vehicle vehicle) {
        VehicleType type = vehicle.getVehicleType();

        return switch (spotType) {
            case SMALL -> type == VehicleType.BIKE;
            case MEDIUM -> type == VehicleType.CAR;
            case LARGE -> type == VehicleType.TRUCK;
            case EV -> type == VehicleType.ELECTRIC;
        };
    }

    public void assignVehicle(Vehicle vehicle) {
        if (!canFitVehicle(vehicle)) {
            throw new IllegalArgumentException("Vehicle does not fit");
        }
        this.currentVehicle = vehicle;
        this.occupied = true;
    }

    public void removeVehicle() {
        this.currentVehicle = null;
        this.occupied = false;
    }

    public boolean isAvailable() {
        return !occupied;
    }

    public String getSpotId() {
        return spotId;
    }

    public SpotType getSpotType() {
        return spotType;
    }

    public Vehicle getCurrentVehicle() {
        return currentVehicle;
    }

    @Override
    public String toString() {
        return "ParkingSpot{" +
                "spotId='" + spotId + '\'' +
                ", spotType=" + spotType +
                ", occupied=" + occupied +
                '}';
    }
}