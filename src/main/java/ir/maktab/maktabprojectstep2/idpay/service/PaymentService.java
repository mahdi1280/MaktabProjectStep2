package ir.maktab.maktabprojectstep2.idpay.service;

import ir.maktab.maktabprojectstep2.idpay.StatusType;
import ir.maktab.maktabprojectstep2.idpay.request.ConfirmRequest;
import ir.maktab.maktabprojectstep2.idpay.request.TransactionCreateCore;
import ir.maktab.maktabprojectstep2.idpay.response.ConfirmResponse;
import ir.maktab.maktabprojectstep2.idpay.response.TransactionCreateResponse;

public interface PaymentService {

    TransactionCreateResponse createTransaction(TransactionCreateCore transactionCreateCore);

    ConfirmResponse confirm(ConfirmRequest confirmRequest);

    StatusType convertStatus(int status);
}
