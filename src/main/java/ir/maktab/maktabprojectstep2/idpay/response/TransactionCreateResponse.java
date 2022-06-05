package ir.maktab.maktabprojectstep2.idpay.response;

public class TransactionCreateResponse {

    private String trackId;
    private String link;

    public TransactionCreateResponse(String trackId, String link) {
        this.trackId = trackId;
        this.link = link;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public static class Builder {

        private String trackId;
        private String link;

        public Builder() {
        }

        public Builder trackId(String trackId) {
            this.trackId = trackId;
            return this;
        }

        public Builder link(String link) {
            this.link = link;
            return this;
        }

        public TransactionCreateResponse build() {
            return new TransactionCreateResponse(trackId, link);
        }
    }
}
