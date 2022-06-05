package ir.maktab.maktabprojectstep2.controller;

import ir.maktab.maktabprojectstep2.core.ErrorMessage;
import ir.maktab.maktabprojectstep2.core.RuleException;
import ir.maktab.maktabprojectstep2.dto.idpyresponserequest.reponse.CallbackResponsePost;
import ir.maktab.maktabprojectstep2.dto.idpyresponserequest.reponse.CallbackTransferResponse;
import ir.maktab.maktabprojectstep2.dto.idpyresponserequest.reponse.PaymentErrorResponse;
import ir.maktab.maktabprojectstep2.idpay.PaymentCalBackStatusIdPay;
import ir.maktab.maktabprojectstep2.idpay.StatusType;
import ir.maktab.maktabprojectstep2.idpay.request.ConfirmRequest;
import ir.maktab.maktabprojectstep2.idpay.response.ConfirmResponse;
import ir.maktab.maktabprojectstep2.idpay.service.PaymentService;
import ir.maktab.maktabprojectstep2.model.StatusTransaction;
import ir.maktab.maktabprojectstep2.model.Transaction;
import ir.maktab.maktabprojectstep2.model.User;
import ir.maktab.maktabprojectstep2.service.status.StatusService;
import ir.maktab.maktabprojectstep2.service.transaction.TransactionService;
import ir.maktab.maktabprojectstep2.service.user.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/payment")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CallbackController {

    private final UserService userService;
    private static final int SUCCESS_CODE_ID_PAY = 10;
    private final TransactionService transactionService;
    private final PaymentService paymentServiceIdPay;
    private final StatusService statusService;
    private final String merchant;


    public CallbackController(UserService userService, TransactionService transactionService
            , PaymentService paymentServiceIdPay, StatusService statusService
            , @Value("${app.idPay.merchant}") String merchant) {
        this.userService = userService;
        this.transactionService = transactionService;
        this.paymentServiceIdPay = paymentServiceIdPay;
        this.statusService = statusService;
        this.merchant = merchant;
    }

    @PostMapping("/callback")
    public String callbackPost(@ModelAttribute CallbackResponsePost callbackResponsePost, Model model) {
        Transaction paymentTransaction = transactionService.findByOrderIdAndTrackId(callbackResponsePost.getOrderId(), callbackResponsePost.getId()).orElseThrow(()
                -> new RuleException(ErrorMessage.error("pay.not.found", "pay.not.found")));
        CallbackTransferResponse callbackTransferResponse = paymentCallBack(paymentTransaction
                , callbackResponsePost.getStatus()
                , callbackResponsePost.getTrackId());
        model.addAttribute("redirect", "1;url=" + callbackTransferResponse.getRedirect());
        confirm(paymentTransaction);
        return "callback";
    }

    private CallbackTransferResponse paymentCallBack(Transaction paymentTransaction, int status, String id) {
        paymentTransaction.setStatusTransaction(convertStatus(status));
        paymentTransaction.setTrackId(id);
        transactionService.save(paymentTransaction);
        return new CallbackTransferResponse(covertResponse(status), paymentTransaction.getRedirect());
    }

    private StatusTransaction convertStatus(int status) {
        return statusService.findByCode(paymentServiceIdPay.convertStatus(status));
    }

    private void confirm(Transaction paymentTransaction) {
        ConfirmResponse confirm = paymentServiceIdPay.confirm(setConfirmRequest(paymentTransaction.getUniqId(), paymentTransaction.getOrderId()));
        paymentTransaction.setStatusTransaction(convertStatus(confirm.getStatus()));
        savePlan(paymentTransaction);
        transactionService.save(paymentTransaction);
    }

    private void savePlan(Transaction transaction) {
        if(transaction.getStatusTransaction().getCode().equals(StatusType.PAYMENT_IS_APPROVED)) {
            User user = userService.findById(transaction.getUserId()).orElse(null);
            user.setCredit(Integer.parseInt(String.valueOf(transaction.getAmount())) + user.getCredit());
            userService.save(user);
        }
    }

    private ConfirmRequest setConfirmRequest(String trackId, String orderId) {
        return ConfirmRequest.builder()
                .orderId(orderId)
                .trackId(trackId)
                .merchant(merchant)
                .build();
    }

    private PaymentErrorResponse covertResponse(int status) {
        return convertResponseIdPay(status);
    }

    private PaymentErrorResponse convertResponseIdPay(int status) {
        return new PaymentErrorResponse(SUCCESS_CODE_ID_PAY == status ? null : PaymentErrorResponse.Error.builder()
                .code(status)
                .message(PaymentCalBackStatusIdPay.CALLBACK_STATUSES.get(status))
                .build());
    }
}
