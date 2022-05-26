package ir.maktab.maktabprojectstep2.controller;

import ir.maktab.maktabprojectstep2.config.SecurityUtil;
import ir.maktab.maktabprojectstep2.dto.request.OfferSaveRequest;
import ir.maktab.maktabprojectstep2.dto.response.OfferFindByOrderResponse;
import ir.maktab.maktabprojectstep2.dto.response.OfferResponse;
import ir.maktab.maktabprojectstep2.model.Offer;
import ir.maktab.maktabprojectstep2.model.User;
import ir.maktab.maktabprojectstep2.service.offer.OfferService;
import ir.maktab.maktabprojectstep2.service.order.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/offer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OfferController {

    private final OfferService offerService;
    private final OrderService orderService;

    public OfferController(OfferService offerService, OrderService orderService) {
        this.offerService = offerService;
        this.orderService = orderService;
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','EXPERT')")
    public ResponseEntity<OfferResponse> save(@Valid @RequestBody OfferSaveRequest offerSaveRequest) {
        Offer offer = offerService.saveOffer(offerSaveRequest);
        return ResponseEntity.ok(new OfferResponse(offer.getId()));
    }

    @GetMapping("/order/{orderId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','EXPERT','CUSTOMER')")
    public ResponseEntity<Page<OfferFindByOrderResponse>> getAllByOrder(@PathVariable long orderId, Pageable pageable) {
        Page<Offer> offers = offerService.findByOrder(orderId, pageable);
        return ResponseEntity.ok(offers.map(this::createOfferFindByOrderResponse));
    }

    @PutMapping("/{offerId}/order/{orderId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','EXPERT','CUSTOMER')")
    public ResponseEntity<OfferResponse> assignOffer(@PathVariable Long offerId, @PathVariable Long orderId) {
        orderService.assignOffer(offerId, orderId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyAuthority('ADMIN','EXPERT')")
    public ResponseEntity<List<OfferFindByOrderResponse>> getAllByUser(){
        User user= SecurityUtil.getCurrentUser();
        List<Offer> offers=offerService.findAllByUser(user);
        return ResponseEntity.ok(offers.stream().map(this::createOfferFindByOrderResponse).collect(Collectors.toList()));
    }

    private OfferFindByOrderResponse createOfferFindByOrderResponse(Offer offer) {
        return OfferFindByOrderResponse.builder()
                .id(offer.getId())
                .periodOfTime(offer.getPeriodOfTime())
                .proposedPrice(offer.getProposedPrice())
                .createdAt(offer.getCreatedAt())
                .startTime(offer.getStartTime())
                .userId(offer.getUser().getId())
                .selected(getSelected(offer))
                .build();
    }

    private boolean getSelected(Offer offer) {
        if(offer.getOrder().getOffer()==null)
            return false;
        return offer.getOrder().getOffer().equals(offer);
    }


}
