package ir.maktab.maktabprojectstep2.service.service;

import ir.maktab.maktabprojectstep2.model.Service;

import java.util.List;
import java.util.Optional;

public interface ServiceService {

    void save(Service service);

    Optional<Service> findById(long id);

    List<Service> findAll();
}
