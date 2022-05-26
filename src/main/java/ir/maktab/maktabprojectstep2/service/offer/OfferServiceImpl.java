package ir.maktab.maktabprojectstep2.service.offer;

import ir.maktab.maktabprojectstep2.core.ErrorMessage;
import ir.maktab.maktabprojectstep2.core.RuleException;
import ir.maktab.maktabprojectstep2.dto.request.OfferSaveRequest;
import ir.maktab.maktabprojectstep2.model.Offer;
import ir.maktab.maktabprojectstep2.model.Order;
import ir.maktab.maktabprojectstep2.model.User;
import ir.maktab.maktabprojectstep2.model.enums.StatusOrder;
import ir.maktab.maktabprojectstep2.repository.OfferRepository;
import ir.maktab.maktabprojectstep2.repository.OrderRepository;
import ir.maktab.maktabprojectstep2.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OfferServiceImpl(OfferRepository offerRepository, OrderRepository orderRepository, UserRepository userRepository) {
        this.offerRepository = offerRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void save(Offer offer) {
        offerRepository.save(offer);
    }

    @Override
    public Offer saveOffer(OfferSaveRequest offerSaveRequest) {
        Order order = orderRepository.findById(offerSaveRequest.getOrderId()).orElseThrow(() -> new RuleException(ErrorMessage.error("order.not.found")));
        if (order.getProposedPrice() > offerSaveRequest.getProposedPrice())
            throw new RuleException(ErrorMessage.error("price.not.valid"));
        User user = userRepository.findById(1L).orElseThrow(() -> new RuleException(ErrorMessage.error("user.nor.found")));
        order.setStatus(StatusOrder.WAITING_FOR_THE_SELECTION);
        orderRepository.save(order);
        Offer offer = createOffer(user, order, offerSaveRequest);
        return offerRepository.save(offer);
    }

    @Override
    public Optional<Offer> findById(long id) {
        return offerRepository.findById(id);
    }

    @Override
    public List<Offer> findAll() {
        return offerRepository.findAll();
    }

    @Override
    public Page<Offer> findByOrder(long orderId, Pageable pageable) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuleException(ErrorMessage.error("order.not.found")));
        return offerRepository.findAllByOrder(order, pageable);
    }

    private Offer createOffer(User user, Order order, OfferSaveRequest offerSaveRequest) {
        return Offer.builder()
                .periodOfTime(offerSaveRequest.getPeriodOfTime())
                .proposedPrice(offerSaveRequest.getProposedPrice())
                .startTime(offerSaveRequest.getStartTime())
                .order(order)
                .user(user)
                .build();
    }
}
