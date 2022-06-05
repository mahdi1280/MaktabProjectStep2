package ir.maktab.maktabprojectstep2.idpay.request;

public class TransactionCreateCore {

    private String orderId;
    private long amount;
    private String merchant;

    public TransactionCreateCore(String orderId, long amount, String merchant) {
        this.orderId = orderId;
        this.amount = amount;
        this.merchant = merchant;
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

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public static class Builder {

        private String orderId;
        private long amount;
        private String merchant;

        protected Builder() {}

        public Builder orderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder amount(long amount) {
            this.amount = amount;
            return this;
        }

        public Builder merchant(String merchant) {
            this.merchant = merchant;
            return this;
        }

        public TransactionCreateCore build() {
            return new TransactionCreateCore(orderId, amount,merchant);
        }
    }
}
