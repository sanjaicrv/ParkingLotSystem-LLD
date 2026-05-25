package model;


import java.util.LinkedHashMap;
import java.util.Map;

public class ParkingFloor {

    private int floorNumber;
    private Map<String, ParkingSpot> spots;

    public ParkingFloor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.spots = new LinkedHashMap<>();
    }

    public void addSpot(ParkingSpot spot) {
        spots.put(spot.getSpotId(), spot);
    }

    public ParkingSpot findAvailableSpot(Vehicle vehicle) {
        for (ParkingSpot spot : spots.values()) {
            if (spot.isAvailable() && spot.canFitVehicle(vehicle)) {
                return spot;
            }
        }
        return null;
    }

    public void displayAvailability() {
        System.out.println("Floor : " + floorNumber);

        for (ParkingSpot spot : spots.values()) {
            System.out.println(spot);
        }
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public Map<String, ParkingSpot> getSpots() {
        return spots;
    }
}
