package br.com.codenation.paymentmethods;

public class CreditCardPriceStrategy implements PriceStrategy {
    private static final Double DISCOUNT_POLICY = 0.98;

    @Override
    public Double calculate(Double price) {
        return price * DISCOUNT_POLICY;
    }
}
