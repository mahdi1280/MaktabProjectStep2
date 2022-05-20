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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderSaveResponse> save(@RequestBody @Valid OrderSaveRequest orderSaveRequest){
        Order order=orderService.saveOrder(orderSaveRequest);
        return ResponseEntity.ok(new OrderSaveResponse(order.getId()));
    }


}
