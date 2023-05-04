package control;

public class DeliveryPay implements PaymentMethod {
    @Override
    public boolean makePayment(Order order) {
        return (order.getTotalPrice() <= 2000);
    }
}

