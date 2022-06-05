package ir.maktab.maktabprojectstep2.dto.idpyresponserequest.reponse;

public class CreateTransactionResponse {

    private String link;

    public CreateTransactionResponse(String link) {
        this.link = link;
    }

    public static Builder builder(){
        return new Builder();
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public static class Builder{

        private String link;

        public Builder link(String link) {
            this.link = link;
            return this;
        }

        public CreateTransactionResponse build(){
            return new CreateTransactionResponse(link);
        }
    }
}
