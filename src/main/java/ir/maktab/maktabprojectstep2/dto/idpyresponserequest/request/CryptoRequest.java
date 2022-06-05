package ir.maktab.maktabprojectstep2.dto.idpyresponserequest.request;

public class CryptoRequest {

    private final long shoppingCartId;
    private final long userId;
    private final long amount;
    private final String orderId;

    public CryptoRequest(long shoppingCartId, long userId, long amount, String orderId) {
        this.shoppingCartId = shoppingCartId;
        this.userId = userId;
        this.amount = amount;
        this.orderId = orderId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public long getShoppingCartId() {
        return shoppingCartId;
    }

    public long getUserId() {
        return userId;
    }

    public long getAmount() {
        return amount;
    }

    public String getOrderId() {
        return orderId;
    }

    public static class Builder {

        private long shoppingCartId;
        private long userId;
        private long amount;
        private String orderId;

        public Builder() {
        }

        public Builder setShoppingCartId(long shoppingCartId) {
            this.shoppingCartId = shoppingCartId;
            return this;
        }

        public Builder setUserId(long userId) {
            this.userId = userId;
            return this;
        }

        public Builder setAmount(long amount) {
            this.amount = amount;
            return this;
        }

        public Builder setOrderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        public CryptoRequest build() {
            return new CryptoRequest(shoppingCartId, userId, amount, orderId);
        }
    }
}
