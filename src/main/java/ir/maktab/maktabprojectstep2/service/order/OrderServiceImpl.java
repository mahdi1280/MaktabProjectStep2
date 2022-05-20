package ir.maktab.maktabprojectstep2.service.order;

import ir.maktab.maktabprojectstep2.core.ErrorMessage;
import ir.maktab.maktabprojectstep2.core.RuleException;
import ir.maktab.maktabprojectstep2.model.Offer;
import ir.maktab.maktabprojectstep2.model.Order;
import ir.maktab.maktabprojectstep2.model.enums.StatusOrder;
import ir.maktab.maktabprojectstep2.repository.OfferRepository;
import ir.maktab.maktabprojectstep2.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final OfferRepository offerRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OfferRepository offerRepository) {
        this.orderRepository = orderRepository;
        this.offerRepository = offerRepository;
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public Optional<Order> findById(long id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public void assignOffer(long offerId, long orderId) {
        Offer offer = offerRepository.findById(offerId).orElseThrow(() -> new RuleException(ErrorMessage.error("offer.not.found")));
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuleException(ErrorMessage.error("order.not.found")));
        if(order.getOffer()!=null){
            throw new RuleException(ErrorMessage.error("order.already.has.offer"));
        }
        order.setStatus(StatusOrder.WAITING_FOR_THE_OFFER);
        order.setOffer(offer);
        orderRepository.save(order);
    }
}
