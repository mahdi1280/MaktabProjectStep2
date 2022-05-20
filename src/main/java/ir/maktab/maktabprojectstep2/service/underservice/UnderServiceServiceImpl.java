package ir.maktab.maktabprojectstep2.service.underservice;

import ir.maktab.maktabprojectstep2.dto.response.UnderServiceResponse;
import ir.maktab.maktabprojectstep2.model.UnderService;
import ir.maktab.maktabprojectstep2.repository.UnderServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnderServiceServiceImpl implements UnderServiceService {

    private final UnderServiceRepository underServiceRepository;

    public UnderServiceServiceImpl(UnderServiceRepository underServiceRepository) {
        this.underServiceRepository = underServiceRepository;
    }

    @Override
    public void save(UnderService underService) {
        this.underServiceRepository.save(underService);
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
    public List<UnderServiceResponse> findByService(ir.maktab.maktabprojectstep2.model.Service service) {
        return underServiceRepository.findByService(service);
    }
}
