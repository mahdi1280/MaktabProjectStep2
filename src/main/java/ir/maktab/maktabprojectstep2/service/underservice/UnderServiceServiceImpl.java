package ir.maktab.maktabprojectstep2.service.underservice;

import ir.maktab.maktabprojectstep2.core.ErrorMessage;
import ir.maktab.maktabprojectstep2.core.RuleException;
import ir.maktab.maktabprojectstep2.dto.request.UnderServiceSaveRequest;
import ir.maktab.maktabprojectstep2.model.UnderService;
import ir.maktab.maktabprojectstep2.model.User;
import ir.maktab.maktabprojectstep2.repository.ServiceRepository;
import ir.maktab.maktabprojectstep2.repository.UnderServiceRepository;
import org.springframework.stereotype.Service;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UnderServiceServiceImpl implements UnderServiceService {

    private final UnderServiceRepository underServiceRepository;
    private final ServiceRepository serviceRepository;

    public UnderServiceServiceImpl(UnderServiceRepository underServiceRepository, ServiceRepository serviceRepository) {
        this.underServiceRepository = underServiceRepository;
        this.serviceRepository = serviceRepository;
    }

    @Override
    public void save(UnderService underService) {
        this.underServiceRepository.save(underService);
    }

    @Override
    public UnderService save(UnderServiceSaveRequest underServiceSaveRequest) {
        ir.maktab.maktabprojectstep2.model.Service service = serviceRepository.findById(underServiceSaveRequest.getServiceId()).orElseThrow(() -> new RuleException(ErrorMessage.error("service.not.found")));
        UnderService underService=createUnderService(service,underServiceSaveRequest);
        return underServiceRepository.save(underService);
    }

    private UnderService createUnderService(ir.maktab.maktabprojectstep2.model.Service service, UnderServiceSaveRequest underServiceSaveRequest) {
        return UnderService.builder()
                .service(service)
                .basePrice(underServiceSaveRequest.getBasePrice())
                .details(underServiceSaveRequest.getTitle())
                .build();
    }

    @Override
    public Optional<UnderService> findById(long id) {
        return underServiceRepository.findById(id);
    }

    @Override
    public List<UnderService> findAll() {
        return underServiceRepository.findAll();
    }

    @Override
    public List<UnderService> findByService(ir.maktab.maktabprojectstep2.model.Service service) {
        return underServiceRepository.findAllByService(service);
    }
}
