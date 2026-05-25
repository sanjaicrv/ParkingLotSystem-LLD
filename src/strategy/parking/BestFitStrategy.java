package strategy.parking;

import model.*;

import java.util.List;

public class BestFitStrategy implements ParkingStrategy {

    @Override
    public ParkingSpot findSpot(List<ParkingFloor> floors, Vehicle vehicle) {

        for (ParkingFloor floor : floors) {
            for (ParkingSpot spot : floor.getSpots().values()) {
                if (spot.isAvailable() && spot.canFitVehicle(vehicle)) {
                    return spot;
                }
            }
        }

        return null;
    }
}
