package ir.maktab.maktabprojectstep2.repository;

import ir.maktab.maktabprojectstep2.model.Order;
import ir.maktab.maktabprojectstep2.model.UnderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findAllByUnderService(UnderService underService, Pageable pageable);
}
