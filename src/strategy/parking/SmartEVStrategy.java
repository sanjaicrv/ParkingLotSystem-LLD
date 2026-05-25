package strategy.parking;
import enums.SpotType;
import model.*;

import java.util.List;

public class SmartEVStrategy implements ParkingStrategy {

    @Override
    public ParkingSpot findSpot(List<ParkingFloor> floors, Vehicle vehicle) {

        for (ParkingFloor floor : floors) {
            for (ParkingSpot spot : floor.getSpots().values()) {

                if (spot.isAvailable()
                        && spot.getSpotType() == SpotType.EV
                        && spot.canFitVehicle(vehicle)) {
                    return spot;
                }
            }
        }

        return null;
    }
}
