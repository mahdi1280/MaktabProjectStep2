package ir.maktab.maktabprojectstep2.service.underservice;

import ir.maktab.maktabprojectstep2.dto.request.UnderServiceSaveRequest;
import ir.maktab.maktabprojectstep2.dto.response.UnderServiceResponse;
import ir.maktab.maktabprojectstep2.model.Service;
import ir.maktab.maktabprojectstep2.model.UnderService;
import ir.maktab.maktabprojectstep2.model.User;

import java.util.List;
import java.util.Optional;

public interface UnderServiceService {

    void save(UnderService underService);

    UnderService save(UnderServiceSaveRequest underServiceSaveRequest);

    Optional<UnderService> findById(long id);

    List<UnderService> findAll();

    List<UnderService> findByService(Service service);

    List<UnderService> findAllByIds(List<Long> ids);
}
