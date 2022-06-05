package ir.maktab.maktabprojectstep2.dto.idpyresponserequest.reponse;

import java.io.Serializable;

public class PaymentErrorResponse implements Serializable {

    private final Error error;

    public PaymentErrorResponse(Error error) {
        this.error = error;
    }

    public Error getError() {
        return error;
    }

    public static class Error {

        private final int code;
        private final String message;

        public Error(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public static Builder builder() {
            return new Builder();
        }

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public static class Builder {

            private int code;
            private String message;

            public Builder() {
            }

            public Builder code(int code) {
                this.code = code;
                return this;
            }

            public Builder message(String message) {
                this.message = message;
                return this;
            }

            public Error build() {
                return new Error(code, message);
            }
        }
    }
}
