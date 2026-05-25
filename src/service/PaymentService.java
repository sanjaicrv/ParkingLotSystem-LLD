package service;


public class PaymentService {

    public boolean pay(double amount) {

        System.out.println("Processing Payment...");
        System.out.println("Amount Paid : " + amount);

        boolean paymentStatus = true;

        if (paymentStatus) {
            System.out.println("Payment Successful");
            return true;
        }

        System.out.println("Payment Failed");

        return false;
    }
}