package ir.maktab.maktabprojectstep2.service.order;

import ir.maktab.maktabprojectstep2.dto.request.OrderSaveRequest;
import ir.maktab.maktabprojectstep2.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    void save(Order order);

    Order saveOrder(OrderSaveRequest orderSaveRequest);

    Optional<Order> findById(long id);

    List<Order> findAll();

    void assignOffer(long offerId,long orderId);
}
