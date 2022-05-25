package ir.maktab.maktabprojectstep2.repository;

import ir.maktab.maktabprojectstep2.dto.response.UnderServiceResponse;
import ir.maktab.maktabprojectstep2.model.Service;
import ir.maktab.maktabprojectstep2.model.UnderService;
import ir.maktab.maktabprojectstep2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UnderServiceRepository extends JpaRepository<UnderService, Long> {

    List<UnderService> findAllByService(Service service);
}
