package control;

import actors.Attachtments.Order;

/**
 * This interface defines one method
 * that allows the user to make a payment
 * through different available payment methods
 */
public interface PaymentMethod {
    boolean makePayment(Order order);
}

