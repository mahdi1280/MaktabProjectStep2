package ir.maktab.maktabprojectstep2.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.io.Serializable;

public class OfferPayRequest implements Serializable {

    private final long offerId;
    private final String redirect;

    @JsonCreator
    public OfferPayRequest(long offerId, String redirect) {
        this.offerId = offerId;
        this.redirect = redirect;
    }

    public long getOfferId() {
        return offerId;
    }

    public String getRedirect() {
        return redirect;
    }
}
