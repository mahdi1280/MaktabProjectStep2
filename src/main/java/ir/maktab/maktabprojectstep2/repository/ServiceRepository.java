package ir.maktab.maktabprojectstep2.repository;

import ir.maktab.maktabprojectstep2.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServiceRepository extends JpaRepository<Service, Long> {
    Optional<Service> findByTitle(String title);
}
