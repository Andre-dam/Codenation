package br.com.codenation.paymentmethods;

public class CashPriceStrategy implements PriceStrategy {
    private static final Double DISCOUNT_POLICY = 0.90;

    @Override
    public Double calculate(Double price) {
        return price * DISCOUNT_POLICY;
    }
}
