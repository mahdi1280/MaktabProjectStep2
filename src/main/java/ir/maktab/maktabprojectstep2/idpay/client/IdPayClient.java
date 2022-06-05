package ir.maktab.maktabprojectstep2.idpay.client;

import ir.maktab.maktabprojectstep2.idpay.config.CustomErrorDecoder;
import ir.maktab.maktabprojectstep2.idpay.request.ConfirmIdPayRequest;
import ir.maktab.maktabprojectstep2.idpay.request.CreateTransactionIdPayRequest;
import ir.maktab.maktabprojectstep2.idpay.response.ConfirmResponse;
import ir.maktab.maktabprojectstep2.idpay.response.CreateTransactionIdPayResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "idpay", url = "${app.idpay.uri}", configuration = {CustomErrorDecoder.class})
public interface IdPayClient {

    @PostMapping
    CreateTransactionIdPayResponse createPayment(@RequestBody CreateTransactionIdPayRequest createTransactionIdPayRequest);

    @PostMapping("/verify")
    ConfirmResponse confirm(@RequestBody ConfirmIdPayRequest confirmIdPayRequest);
}
