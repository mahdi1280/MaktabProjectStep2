package ir.maktab.maktabprojectstep2.idpay.request;

public class ConfirmRequest {

    private String trackId;
    private String orderId;
    private String merchant;

    public ConfirmRequest(String trackId, String orderId, String merchant) {
        this.trackId = trackId;
        this.orderId = orderId;
        this.merchant = merchant;
    }

    public static Builder builder() {
        return new Builder();
    }


    public String getTrackId() {
        return  trackId;
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

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public static class Builder {

        private String trackId;
        private String orderId;
        private String merchant;

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

        public Builder merchant(String merchant) {
            this.merchant = merchant;
            return this;
        }

        public ConfirmRequest build() {
            return new ConfirmRequest(trackId, orderId,merchant);
        }
    }
}
