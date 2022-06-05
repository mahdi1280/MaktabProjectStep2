package ir.maktab.maktabprojectstep2.controller;

import ir.maktab.maktabprojectstep2.config.SecurityUtil;
import ir.maktab.maktabprojectstep2.core.ErrorMessage;
import ir.maktab.maktabprojectstep2.core.RuleException;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.UUID;

@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OfferPayController {

    private final IdPayService idPayService;
    private final String merchant;
    private final OfferService offerService;
    private final TransactionService transactionService;

    public OfferPayController(IdPayService idPayService
            , @Value("${app.idPay.merchant}") String merchant, OfferService offerService, TransactionService transactionService) {
        this.idPayService = idPayService;
        this.merchant = merchant;
        this.offerService = offerService;
        this.transactionService = transactionService;
    }

    @PostMapping("/buy")
    @PreAuthorize("hasAnyAuthority('ADMIN','CUSTOMER','EXPERT')")
    public ResponseEntity<TransactionLinkResponse> save(@RequestBody @Valid OfferPayRequest offerPayRequest) {
        User currentUser = SecurityUtil.getCurrentUser();
        Offer offer = offerService.findById(offerPayRequest.getOfferId()).orElseThrow(() -> new RuleException(ErrorMessage.error("offer.not.found")));
        String orderId = UUID.randomUUID().toString();
        TransactionCreateResponse transaction = idPayService.createTransaction(
                createTransactionRequest(offer, orderId));
        transactionService.save(createTransaction(currentUser, offer, orderId, transaction, offerPayRequest));
        return ResponseEntity.ok(createTransactionLinkResponse(transaction));
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
