package ir.maktab.maktabprojectstep2.service.offer;

import ir.maktab.maktabprojectstep2.dto.request.OfferSaveRequest;
import ir.maktab.maktabprojectstep2.model.Offer;
import ir.maktab.maktabprojectstep2.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface OfferService {

    void save(Offer offer);

    Offer saveOffer(OfferSaveRequest offerSaveRequest);

    Optional<Offer> findById(long id);

    List<Offer> findAll();

    Page<Offer> findByOrder(long order, Pageable pageable);

    List<Offer> findAllByUser(User user);
}
