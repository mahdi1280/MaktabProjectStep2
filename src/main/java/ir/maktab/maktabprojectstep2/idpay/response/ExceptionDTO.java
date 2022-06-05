package ir.maktab.maktabprojectstep2.idpay.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ExceptionDTO {

    private final String errorCode;
    private final String errorMessage;

    @JsonCreator
    public ExceptionDTO(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @JsonProperty("error_code")
    public String getErrorCode() {
        return errorCode;
    }

    @JsonProperty("error_message")
    public String getErrorMessage() {
        return errorMessage;
    }

}
