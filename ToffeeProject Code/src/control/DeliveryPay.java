package control;
import actors.Attachtments.Order;

/**
 * This class represents the pay on
 * delivery option.
 *
 * @author Maya Ayman
 */
public class DeliveryPay implements PaymentMethod {
    /**
     * This method allows the user to
     * make a payment.
     * @param order
     * @return boolean This shows whether the payment was successful.
     */
    @Override
    public boolean makePayment(Order order) {
        return (order.getTotalPrice() <= 2000);
    }
}

