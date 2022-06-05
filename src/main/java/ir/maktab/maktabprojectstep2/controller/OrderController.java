package ir.maktab.maktabprojectstep2.controller;

import ir.maktab.maktabprojectstep2.config.SecurityUtil;
import ir.maktab.maktabprojectstep2.dto.request.OrderSaveRequest;
import ir.maktab.maktabprojectstep2.dto.response.OrderFindResponse;
import ir.maktab.maktabprojectstep2.dto.response.OrderSaveResponse;
import ir.maktab.maktabprojectstep2.model.Order;
import ir.maktab.maktabprojectstep2.model.User;
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
@RequestMapping("/order")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','CUSTOMER')")
    public ResponseEntity<OrderSaveResponse> save(@RequestBody @Valid OrderSaveRequest orderSaveRequest) {
        Order order = orderService.saveOrder(orderSaveRequest);
        return ResponseEntity.ok(new OrderSaveResponse(order.getId()));
    }

    @GetMapping("/{underServiceId}")
    public ResponseEntity<Page<OrderFindResponse>> getOrder(@PathVariable long underServiceId, Pageable pageable){
        Page<Order> orderPage = orderService.findByUnderServiceId(underServiceId,pageable);
        return ResponseEntity.ok(orderPage.map(this::createOrderResponse));
    }

    @GetMapping("/myOrder")
    @PreAuthorize("hasAnyAuthority('ADMIN','CUSTOMER')")
    public ResponseEntity<List<OrderFindResponse>> getMyOrder(){
        User user= SecurityUtil.getCurrentUser();
        List<Order> allByUser = orderService.findAllByUser(user);
        return ResponseEntity.ok(allByUser.stream().map(this::createOrderResponse).collect(Collectors.toList()));
    }

    private OrderFindResponse createOrderResponse(Order order) {
        return OrderFindResponse.builder()
                .id(order.getId())
                .address(order.getAddress())
                .proposedPrice(order.getProposedPrice())
                .workTime(order.getWordTime())
                .build();
    }


}
