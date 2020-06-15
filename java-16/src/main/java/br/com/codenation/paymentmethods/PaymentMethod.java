package br.com.codenation.paymentmethods;

public enum PaymentMethod {

    CASH(new CashPriceStrategy()),
    DEBIT_CARD(new DebitCardPriceStrategy()),
    CREDIT_CARD(new CreditCardPriceStrategy()),
    TRANSFER(new TransferPriceStrategy());

    private final PriceStrategy priceStrategy;

    PaymentMethod(PriceStrategy priceStrategy) {
        this.priceStrategy = priceStrategy;
    }

    public PriceStrategy getPaymentStrategy() {
        return priceStrategy;
    }
}