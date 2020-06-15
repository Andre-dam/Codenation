package br.com.codenation.paymentmethods;

public class TransferPriceStrategy implements PriceStrategy {
    private static final Double DISCOUNT_POLICY = 0.92;

    @Override
    public Double calculate(Double price) {
        return price * DISCOUNT_POLICY;
    }
}
