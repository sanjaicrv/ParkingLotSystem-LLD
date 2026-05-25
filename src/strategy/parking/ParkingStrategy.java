package strategy.parking;


import model.*;

import java.util.List;

public interface ParkingStrategy {

    ParkingSpot findSpot(List<ParkingFloor> floors, Vehicle vehicle);
}
