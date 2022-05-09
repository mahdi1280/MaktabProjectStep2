package ir.maktab.maktabprojectstep2.service.offer;

import ir.maktab.maktabprojectstep2.model.Offer;

import java.util.List;
import java.util.Optional;

public interface OfferService {

    void save(Offer offer);

    Optional<Offer> findById(long id);

    List<Offer> findAll();
}