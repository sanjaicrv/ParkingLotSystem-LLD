package strategy.fee;

import model.ParkingTicket;

public class HourlyFeeStrategy implements FeeStrategy {

    private static final double RATE_PER_HOUR = 50.0;

    @Override
    public double calculateFee(ParkingTicket ticket) {
        return ticket.calculateDuration() * RATE_PER_HOUR;
    }
}
