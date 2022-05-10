package ir.maktab.maktabprojectstep2.controller;

import ir.maktab.maktabprojectstep2.core.ErrorMessage;
import ir.maktab.maktabprojectstep2.core.RuleException;
import ir.maktab.maktabprojectstep2.dto.request.OfferSaveRequest;
import ir.maktab.maktabprojectstep2.dto.response.OfferFindByOrderResponse;
import ir.maktab.maktabprojectstep2.dto.response.OfferResponse;
import ir.maktab.maktabprojectstep2.model.Offer;
import ir.maktab.maktabprojectstep2.model.Order;
import ir.maktab.maktabprojectstep2.model.User;
import ir.maktab.maktabprojectstep2.service.offer.OfferService;
import ir.maktab.maktabprojectstep2.service.order.OrderService;
import ir.maktab.maktabprojectstep2.service.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/offer")
public class OfferController {

    private final OfferService offerService;
    private final OrderService orderService;
    private final UserService userService;

    public OfferController(OfferService offerService, OrderService orderService, UserService userService) {
        this.offerService = offerService;
        this.orderService = orderService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<OfferResponse> save(@Valid @RequestBody OfferSaveRequest offerSaveRequest){
        Order order = orderService.findById(offerSaveRequest.getOrderId()).orElseThrow(() -> new RuleException(ErrorMessage.error("order.not.found")));
        User user=userService.findById(1L).orElseThrow(()->new RuleException(ErrorMessage.error("user.nor.found")));
        Offer offer=createOffer(user,order,offerSaveRequest);
        offerService.save(offer);
        return ResponseEntity.ok(new OfferResponse(offer.getId()));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<Page<OfferFindByOrderResponse>> getAllByOrder(@PathVariable Long orderId, Pageable pageable){
        Order order = orderService.findById(orderId).orElseThrow(() -> new RuleException(ErrorMessage.error("order.not.found")));
        Page<Offer> offers=offerService.findByOrder(order,pageable);
        return ResponseEntity.ok(offers.map(this::createOfferFindByOrderResponse));
    }

    private OfferFindByOrderResponse createOfferFindByOrderResponse(Offer offer) {
        return OfferFindByOrderResponse.builder()
                .id(offer.getId())
                .periodOfTime(offer.getPeriodOfTime())
                .proposedPrice(offer.getProposedPrice())
                .createdAt(offer.getCreatedAt())
                .startTime(offer.getStartTime())
                .userId(offer.getUser().getId())
                .build();
    }

    private Offer createOffer(User user,Order order,OfferSaveRequest offerSaveRequest) {
        return Offer.builder()
                .periodOfTime(offerSaveRequest.getPeriodOfTime())
                .proposedPrice(offerSaveRequest.getProposedPrice())
                .startTime(offerSaveRequest.getStartTime())
                .order(order)
                .user(user)
                .build();
    }
}
