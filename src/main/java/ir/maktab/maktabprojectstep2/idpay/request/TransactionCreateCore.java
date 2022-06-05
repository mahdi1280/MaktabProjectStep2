package ir.maktab.maktabprojectstep2.idpay.request;

public class TransactionCreateCore {

    private String orderId;
    private long amount;

    public TransactionCreateCore(String orderId, long amount) {
        this.orderId = orderId;
        this.amount = amount;
    }

    public static Builder builder(){
        return new Builder();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public static class Builder {

        private String orderId;
        private long amount;

        private Builder() {
        }

        public Builder orderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder amount(long amount) {
            this.amount = amount;
            return this;
        }

        public TransactionCreateCore build() {
            return new TransactionCreateCore(orderId, amount);
        }
    }
}
