package strategy.fee;

import enums.VehicleType;
import model.ParkingTicket;

import java.util.HashMap;
import java.util.Map;

public class VehicleBasedFeeStrategy implements FeeStrategy {

    private Map<VehicleType, Double> rates = new HashMap<>();

    public VehicleBasedFeeStrategy() {
        rates.put(VehicleType.BIKE, 20.0);
        rates.put(VehicleType.CAR, 50.0);
        rates.put(VehicleType.TRUCK, 100.0);
        rates.put(VehicleType.ELECTRIC, 40.0);
    }

    @Override
    public double calculateFee(ParkingTicket ticket) {

        VehicleType type = ticket.getVehicle().getVehicleType();

        return ticket.calculateDuration() * rates.get(type);
    }
}
