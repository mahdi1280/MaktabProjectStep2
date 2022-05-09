package ir.maktab.maktabprojectstep2.service.service;

import ir.maktab.maktabprojectstep2.model.Service;
import ir.maktab.maktabprojectstep2.repository.ServiceRepository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

    public ServiceServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    private final ServiceRepository serviceRepository;


    @Override
    public void save(Service service) {
        serviceRepository.save(service);
    }

    @Override
    public Optional<Service> findById(long id) {
        return serviceRepository.findById(id);
    }

    @Override
    public List<Service> findAll() {
        return serviceRepository.findAll();
    }
}
