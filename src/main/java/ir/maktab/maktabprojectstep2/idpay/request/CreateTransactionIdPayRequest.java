package ir.maktab.maktabprojectstep2.idpay.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateTransactionIdPayRequest {

    private String orderId;
    private long amount;
    private String callback;

    public CreateTransactionIdPayRequest(String orderId, long amount, String callback) {
        this.orderId = orderId;
        this.amount = amount;
        this.callback = callback;
    }

    public static Builder builder(){
        return new Builder();
    }

    @JsonProperty("order_id")
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

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public static class Builder{

        private String orderId;
        private long amount;
        private String callback;

        public Builder orderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder amount(long amount) {
            this.amount = amount;
            return this;
        }

        public Builder callback(String callback) {
            this.callback = callback;
            return this;
        }

        public CreateTransactionIdPayRequest build(){
            return new CreateTransactionIdPayRequest(orderId,amount,callback);
        }
    }
}
