package strategy.fee;


import model.ParkingTicket;

public interface FeeStrategy {

    double calculateFee(ParkingTicket ticket);
}
