package ir.maktab.maktabprojectstep2.idpay.request;

public class ConfirmRequest {

    private String trackId;
    private String orderId;

    public ConfirmRequest(String trackId, String orderId) {
        this.trackId = trackId;
        this.orderId = orderId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public static class Builder {

        private String trackId;
        private String orderId;

        private Builder() {
        }

        public Builder trackId(String trackId) {
            this.trackId = trackId;
            return this;
        }

        public Builder orderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        public ConfirmRequest build() {
            return new ConfirmRequest(trackId, orderId);
        }
    }
}
