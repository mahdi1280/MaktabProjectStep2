package ir.maktab.maktabprojectstep2.dto.idpyresponserequest.reponse;

public class CallbackTransferResponse {

    private final PaymentErrorResponse paymentErrorResponse;
    private final String redirect;

    public CallbackTransferResponse(PaymentErrorResponse paymentErrorResponse, String redirect) {
        this.paymentErrorResponse = paymentErrorResponse;
        this.redirect = redirect;
    }

    public PaymentErrorResponse getPaymentErrorResponse() {
        return paymentErrorResponse;
    }

    public String getRedirect() {
        return redirect;
    }
}
