package ir.maktab.maktabprojectstep2.dto.idpyresponserequest.request;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Map;

public class CreateTransactionRequest {

    private final Map<String,String> metaData;
    private final long amount;
    private final String redirect;
    private final long gateway;

    @JsonCreator
    public CreateTransactionRequest(Map<String,String> metaData, long amount, String redirect, long gateway) {
        this.metaData=metaData;
        this.amount = amount;
        this.redirect = redirect;
        this.gateway = gateway;
    }

    @NotNull(message = "{create.transaction.request.order.id.null}")
    @NotEmpty(message = "{create.transaction.request.order.empty}")
    public Map<String, String> getMetaData() {
        return metaData;
    }

    @Min(value = 1000, message = "{create.transaction.request.amount.min}")
    public long getAmount() {
        return amount;
    }

    @NotNull(message = "{create.transaction.request.callback.null}")
    @NotBlank(message = "{create.transaction.request.callback.blank}")
    public String getRedirect() {
        return redirect;
    }

    public long getGateway() {
        return gateway;
    }
}
