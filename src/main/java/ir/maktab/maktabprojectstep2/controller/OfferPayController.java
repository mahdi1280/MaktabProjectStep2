package ir.maktab.maktabprojectstep2.controller;

import com.gd.captchaclient.CaptchaVerify;
import ir.maktab.maktabprojectstep2.config.SecurityUtil;
import com.gd.core.ErrorMessage;
import com.gd.core.RuleException;
import ir.maktab.maktabprojectstep2.dto.request.OfferPayRequest;
import ir.maktab.maktabprojectstep2.dto.response.TransactionLinkResponse;
import ir.maktab.maktabprojectstep2.idpay.request.TransactionCreateCore;
import ir.maktab.maktabprojectstep2.idpay.response.TransactionCreateResponse;
import ir.maktab.maktabprojectstep2.idpay.service.IdPayService;
import ir.maktab.maktabprojectstep2.model.Offer;
import ir.maktab.maktabprojectstep2.model.Transaction;
import ir.maktab.maktabprojectstep2.model.User;
import ir.maktab.maktabprojectstep2.service.offer.OfferService;
import ir.maktab.maktabprojectstep2.service.transaction.TransactionService;
import ir.maktab.maktabprojectstep2.service.user.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/offer/pay")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OfferPayController {

    private final IdPayService idPayService;
    private final String merchant;
    private final OfferService offerService;
    private final TransactionService transactionService;
    private final UserService userService;

    public OfferPayController(IdPayService idPayService
            , @Value("${app.idPay.merchant}") String merchant, OfferService offerService, TransactionService transactionService, UserService userService) {
        this.idPayService = idPayService;
        this.merchant = merchant;
        this.offerService = offerService;
        this.transactionService = transactionService;
        this.userService = userService;
    }

    @PostMapping("/buy")
    @PreAuthorize("hasAnyAuthority('ADMIN','CUSTOMER','EXPERT')")
    @CaptchaVerify
    public ResponseEntity<TransactionLinkResponse> save(@RequestBody @Valid OfferPayRequest offerPayRequest) {
        User currentUser = SecurityUtil.getCurrentUser();
        Offer offer = offerService.findById(offerPayRequest.getOfferId()).orElseThrow(() -> new RuleException(ErrorMessage.error("offer.not.found")));
        String orderId = UUID.randomUUID().toString();
        TransactionCreateResponse transaction = idPayService.createTransaction(
                createTransactionRequest(offer, orderId));
        transactionService.save(createTransaction(currentUser, offer, orderId, transaction, offerPayRequest));
        return ResponseEntity.ok(createTransactionLinkResponse(transaction));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','CUSTOMER','EXPERT')")
    @CaptchaVerify
    public ResponseEntity<String> pay(@RequestBody @Valid OfferPayRequest offerPayRequest) {
        User currentUser = SecurityUtil.getCurrentUser();
        Offer offer = offerService.findById(offerPayRequest.getOfferId()).orElseThrow(() -> new RuleException(ErrorMessage.error("offer.not.found")));
        if(currentUser.getCredit() <  offer.getProposedPrice())
            throw new RuleException(ErrorMessage.error("pay.not.enuh"));
        currentUser.setCredit(currentUser.getCredit() -offer.getProposedPrice() );
        userService.save(currentUser);
        offer.getUser().setCredit(offer.getUser().getCredit() + offer.getProposedPrice());
        userService.save(offer.getUser());
        return ResponseEntity.ok("ok");
    }

    private TransactionCreateCore createTransactionRequest(Offer offer, String orderId) {
        return TransactionCreateCore.builder()
                .amount(offer.getProposedPrice())
                .orderId(orderId)
                .merchant(merchant)
                .build();
    }

    public Transaction createTransaction(User currentUser, Offer offer, String orderId, TransactionCreateResponse transactionCreateResponse, OfferPayRequest planSaveRequest) {
        return Transaction.builder()
                .trackId(transactionCreateResponse.getTrackId())
                .orderId(orderId)
                .amount(offer.getProposedPrice())
                .statusTransaction(null)
                .redirect(planSaveRequest.getRedirect())
                .uniqId(transactionCreateResponse.getTrackId())
                .userId(currentUser.getId())
                .planId(offer.getId())
                .build();
    }

    private TransactionLinkResponse createTransactionLinkResponse(TransactionCreateResponse transaction) {
        return TransactionLinkResponse.builder()
                .link(transaction.getLink())
                .build();
    }

}
