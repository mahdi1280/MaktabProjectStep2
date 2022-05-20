package ir.maktab.maktabprojectstep2.service.order;

import ir.maktab.maktabprojectstep2.core.ErrorMessage;
import ir.maktab.maktabprojectstep2.core.RuleException;
import ir.maktab.maktabprojectstep2.dto.request.OrderSaveRequest;
import ir.maktab.maktabprojectstep2.model.Offer;
import ir.maktab.maktabprojectstep2.model.Order;
import ir.maktab.maktabprojectstep2.model.UnderService;
import ir.maktab.maktabprojectstep2.model.User;
import ir.maktab.maktabprojectstep2.model.enums.StatusOrder;
import ir.maktab.maktabprojectstep2.repository.OfferRepository;
import ir.maktab.maktabprojectstep2.repository.OrderRepository;
import ir.maktab.maktabprojectstep2.repository.UnderServiceRepository;
import ir.maktab.maktabprojectstep2.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OfferRepository offerRepository;
    private final UserRepository userRepository;
    private final UnderServiceRepository underServiceRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OfferRepository offerRepository, UserRepository userRepository, UnderServiceRepository underServiceRepository) {
        this.orderRepository = orderRepository;
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.underServiceRepository = underServiceRepository;
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public Order saveOrder(OrderSaveRequest orderSaveRequest) {
        UnderService underService = underServiceRepository.findById(orderSaveRequest.getUnderServiceId())
                .orElseThrow(() -> new RuleException(ErrorMessage.error("under.service.not.found")));
        if (underService.getBasePrice() < orderSaveRequest.getProposedPrice())
            throw new RuleException(ErrorMessage.error("price.not.valid"));
        User user = userRepository.findById(1L).orElseThrow(() -> new RuleException(ErrorMessage.error("user.not.found")));
        Order order = createOrder(user, underService, orderSaveRequest);
        return orderRepository.save(order);
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
        if (order.getOffer() != null) {
            throw new RuleException(ErrorMessage.error("order.already.has.offer"));
        }
        order.setStatus(StatusOrder.WAITING_FOR_THE_OFFER);
        order.setOffer(offer);
        orderRepository.save(order);
    }

    @Override
    public Page<Order> findByUnderServiceId(long underServiceId, Pageable pageable) {
        UnderService underService = underServiceRepository.findById(underServiceId).orElseThrow(() -> new RuleException(ErrorMessage.error("under.service.not.found")));
        return  orderRepository.findAllByUnderService(underService,pageable);
    }

    private Order createOrder(User user, UnderService underService, OrderSaveRequest orderSaveRequest) {
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
