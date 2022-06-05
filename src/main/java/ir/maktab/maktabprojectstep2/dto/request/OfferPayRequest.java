package ir.maktab.maktabprojectstep2.dto.request;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class OfferPayRequest implements Serializable {

    private long offerId;
    private String redirect;
    private String captchaId;
    private String captchaParam;

    public long getOfferId() {
        return offerId;
    }

    public void setOfferId(long offerId) {
        this.offerId = offerId;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public String getCaptchaId() {
        return captchaId;
    }

    public void setCaptchaId(String captchaId) {
        this.captchaId = captchaId;
    }

    public void setCaptchaParam(String captchaParam) {
        this.captchaParam = captchaParam;
    }

    @NotNull(message = "{offer.request.captcha.null}")
    @NotNull(message = "{offer.request.captcha.blank}")
    public String getCaptchaParam() {
        return captchaParam;
    }
}
