package ir.maktab.maktabprojectstep2.idpay.service;

import ir.maktab.maktabprojectstep2.idpay.StatusType;
import ir.maktab.maktabprojectstep2.idpay.client.IdPayClient;
import ir.maktab.maktabprojectstep2.idpay.request.ConfirmIdPayRequest;
import ir.maktab.maktabprojectstep2.idpay.request.ConfirmRequest;
import ir.maktab.maktabprojectstep2.idpay.request.CreateTransactionIdPayRequest;
import ir.maktab.maktabprojectstep2.idpay.request.TransactionCreateCore;
import ir.maktab.maktabprojectstep2.idpay.response.ConfirmResponse;
import ir.maktab.maktabprojectstep2.idpay.response.CreateTransactionIdPayResponse;
import ir.maktab.maktabprojectstep2.idpay.response.TransactionCreateResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class IdPayService implements PaymentService {

    private final IdPayClient idPayClient;
    private final String callbackUrl;

    public IdPayService(IdPayClient idPayClient
            , @Value("${app.idpay.callback.uri}") String callbackUrl) {
        this.idPayClient = idPayClient;
        this.callbackUrl = callbackUrl;
    }

    @Override
    public TransactionCreateResponse createTransaction(TransactionCreateCore transactionCreateCore) {
        CreateTransactionIdPayRequest build = setCreateTransactionRequest(transactionCreateCore);
        CreateTransactionIdPayResponse payment = idPayClient.createPayment(build);
        return setCreateTransactionResponse(payment);
    }

    @Override
    public ConfirmResponse confirm(ConfirmRequest confirmRequest) {
        return idPayClient.confirm(setConfirmResponse(confirmRequest));
    }

    @Override
    public StatusType convertStatus(int status) {
        switch (status) {
            case 1:
                return StatusType.PAYMENT_NOT_MADE;
            case 2:
                return StatusType.PAYMENT_FAILED;
            case 3:
                return StatusType.AN_ERROR_HAS_OCCURRED;
            case 4:
                return StatusType.BLOCKED;
            case 5:
                return StatusType.RETURN_TO_PAYER;
            case 6:
                return StatusType.REVERSED_SYSTEM;
            case 7:
                return StatusType.CANCEL_PAYMENT;
            case 8:
                return StatusType.MOVED_TO_PAYMENT_GATEWAY;
            case 10:
                return StatusType.AWAITING_PAYMENT_CONFIRMATION;
            case 100:
                return StatusType.PAYMENT_IS_APPROVED;
            case 101:
                return StatusType.PAYMENT_HAS_ALREADY_BEEN_APPROVED;
            case 200:
                return StatusType.DEPOSITED_TO_THE_RECIPIENT;
            default:
                return null;
        }
    }

    private ConfirmIdPayRequest setConfirmResponse(ConfirmRequest confirmRequest) {
        return ConfirmIdPayRequest.builder()
                .id(confirmRequest.getTrackId())
                .orderId(confirmRequest.getOrderId())
                .build();
    }

    private TransactionCreateResponse setCreateTransactionResponse(CreateTransactionIdPayResponse payment) {
        return TransactionCreateResponse.builder()
                .link(payment.getLink())
                .trackId(payment.getId())
                .build();
    }

    private CreateTransactionIdPayRequest setCreateTransactionRequest(TransactionCreateCore transactionCreateCore) {
        return CreateTransactionIdPayRequest.builder()
                .amount(transactionCreateCore.getAmount())
                .callback(callbackUrl)
                .orderId(transactionCreateCore.getOrderId())
                .build();
    }
}
