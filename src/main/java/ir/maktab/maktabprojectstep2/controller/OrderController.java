package ir.maktab.maktabprojectstep2.controller;

import ir.maktab.maktabprojectstep2.core.ErrorMessage;
import ir.maktab.maktabprojectstep2.core.RuleException;
import ir.maktab.maktabprojectstep2.dto.request.OrderSaveRequest;
import ir.maktab.maktabprojectstep2.dto.response.OrderSaveResponse;
import ir.maktab.maktabprojectstep2.model.Order;
import ir.maktab.maktabprojectstep2.model.UnderService;
import ir.maktab.maktabprojectstep2.model.User;
import ir.maktab.maktabprojectstep2.model.enums.StatusOrder;
import ir.maktab.maktabprojectstep2.service.order.OrderService;
import ir.maktab.maktabprojectstep2.service.underservice.UnderServiceService;
import ir.maktab.maktabprojectstep2.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final UnderServiceService underServiceService;
    private final UserService userService;

    public OrderController(OrderService orderService, UnderServiceService underServiceService, UserService userService) {
        this.orderService = orderService;
        this.underServiceService = underServiceService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<OrderSaveResponse> save(@RequestBody @Valid OrderSaveRequest orderSaveRequest){
        UnderService underService = underServiceService.findById(orderSaveRequest.getUnderServiceId()).orElseThrow(() -> new RuleException(ErrorMessage.error("under.service.not.found")));
        User user = userService.findById(1L).orElseThrow(() -> new RuleException(ErrorMessage.error("user.not.found")));
        Order order=createOrder(user,underService,orderSaveRequest);
        orderService.save(order);
        return ResponseEntity.ok(new OrderSaveResponse(order.getId()));
    }

    private Order createOrder(User user,UnderService underService,OrderSaveRequest orderSaveRequest) {
        return Order.builder()
                .proposedPrice(orderSaveRequest.getProposedPrice())
                .address(orderSaveRequest.getAddress())
                .wordTime(orderSaveRequest.getWorkTime())
                .underService(underService)
                .status(StatusOrder.WAITING_FOR_THE_OFFER)
                .user(user) //todo change user id
                .build();
    }
}
