package ir.maktab.maktabprojectstep2.service.service;

import ir.maktab.maktabprojectstep2.core.ErrorMessage;
import ir.maktab.maktabprojectstep2.core.RuleException;
import ir.maktab.maktabprojectstep2.dto.request.ServiceSaveRequest;
import ir.maktab.maktabprojectstep2.model.Service;
import ir.maktab.maktabprojectstep2.repository.ServiceRepository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;

    public ServiceServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public void save(Service service) {
        serviceRepository.save(service);
    }

    @Override
    public Service save(ServiceSaveRequest serviceSaveRequest) {
        Optional<Service> byTitle = serviceRepository.findByTitle(serviceSaveRequest.getTitle());
        if (byTitle.isPresent())
            throw new RuleException(ErrorMessage.error("title.is.exist"));
        return serviceRepository.save(createService(serviceSaveRequest));
    }

    private Service createService(ServiceSaveRequest serviceSaveRequest) {
        return Service.builder()
                .title(serviceSaveRequest.getTitle())
                .build();
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
