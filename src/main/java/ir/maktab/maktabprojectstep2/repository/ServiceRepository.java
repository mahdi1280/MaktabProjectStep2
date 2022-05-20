package ir.maktab.maktabprojectstep2.repository;

import ir.maktab.maktabprojectstep2.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Long> {
}
