package control;
import actors.Attachtments.Order;

public class DeliveryPay implements PaymentMethod {
    @Override
    public boolean makePayment(Order order) {
        return (order.getTotalPrice() <= 2000);
    }
}

