package strategy.parking;

import model.*;

import java.util.List;

public class NearestSpotStrategy implements ParkingStrategy {

    @Override
    public ParkingSpot findSpot(List<ParkingFloor> floors, Vehicle vehicle) {

        for (ParkingFloor floor : floors) {
            ParkingSpot spot = floor.findAvailableSpot(vehicle);

            if (spot != null) {
                return spot;
            }
        }

        return null;
    }
}
