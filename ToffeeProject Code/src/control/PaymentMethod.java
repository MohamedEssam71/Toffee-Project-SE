package control;

import actors.Attachtments.Order;

public interface PaymentMethod {
    boolean makePayment(Order order);
}

