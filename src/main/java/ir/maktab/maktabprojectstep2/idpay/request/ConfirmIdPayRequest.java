package ir.maktab.maktabprojectstep2.idpay.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConfirmIdPayRequest {

    private String id;
    private String orderId;

    public ConfirmIdPayRequest(String id, String orderId) {
        this.id = id;
        this.orderId = orderId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("order_id")
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public static class Builder {

        private String id;
        private String orderId;

        private Builder() {
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder orderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        public ConfirmIdPayRequest build() {
            return new ConfirmIdPayRequest(id, orderId);
        }
    }
}

