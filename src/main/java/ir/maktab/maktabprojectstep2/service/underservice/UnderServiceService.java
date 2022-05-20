package ir.maktab.maktabprojectstep2.service.underservice;

import ir.maktab.maktabprojectstep2.dto.response.UnderServiceResponse;
import ir.maktab.maktabprojectstep2.model.Service;
import ir.maktab.maktabprojectstep2.model.UnderService;

import java.util.List;
import java.util.Optional;

public interface UnderServiceService {

    void save(UnderService underService);

    Optional<UnderService> findById(long id);

    List<UnderService> findAll();

    List<UnderServiceResponse> findByService(Service service);
}
