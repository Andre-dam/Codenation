package br.com.codenation;

import br.com.codenation.paymentmethods.PaymentMethod;

import java.util.Optional;

public class BillingProcessor {

    public Double calculate(Order order) {
        return Optional.ofNullable(order.getPaymentMethod())
                .map(PaymentMethod::getPaymentStrategy)
                .orElseThrow(() -> new RuntimeException("Payment method not implemented"))
                .calculate(order.getPrice());
    }
}