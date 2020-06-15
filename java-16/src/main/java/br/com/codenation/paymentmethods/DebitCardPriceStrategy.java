package br.com.codenation.paymentmethods;

public class DebitCardPriceStrategy implements PriceStrategy {
    private static final Double DISCOUNT_POLICY = 0.95;

    @Override
    public Double calculate(Double price) {
        return price * DISCOUNT_POLICY;
    }
}
