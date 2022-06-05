package ir.maktab.maktabprojectstep2.idpay;

import java.util.HashMap;
import java.util.Map;

public class PaymentCalBackStatusIdPay {

    public static final Map<Integer, String> CALLBACK_STATUSES;

    static {
        CALLBACK_STATUSES = new HashMap<>();
        CALLBACK_STATUSES.put(1, "idPay.payment.not.made");
        CALLBACK_STATUSES.put(2, "idPay.payment.fail");
        CALLBACK_STATUSES.put(3, "idPay.payment.error");
        CALLBACK_STATUSES.put(4, "idPay.payment.block");
        CALLBACK_STATUSES.put(5, "idPay.payment.return.to.payer");
        CALLBACK_STATUSES.put(6, "idPay.payment.reversed.system");
        CALLBACK_STATUSES.put(7, "idPay.payment.cancel.payment");
        CALLBACK_STATUSES.put(8, "idPay.payment.moved.to.payment.gateway");
        CALLBACK_STATUSES.put(10, "idPay.payment.awaiting.payment.confirmation");
        CALLBACK_STATUSES.put(100, "idPay.payment.is.approved");
        CALLBACK_STATUSES.put(101, "idPay.payment.has.already.been.approved");
        CALLBACK_STATUSES.put(200, "idPay.payment.deposited.to.the.recipient");
    }
}
