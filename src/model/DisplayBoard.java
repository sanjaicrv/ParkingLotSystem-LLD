
package model;

import java.util.List;

public class DisplayBoard {

    private List<ParkingFloor> floors;

    public DisplayBoard(List<ParkingFloor> floors) {
        this.floors = floors;
    }

    public void showAvailableSpots() {

        for (ParkingFloor floor : floors) {
            floor.displayAvailability();
        }
    }
}