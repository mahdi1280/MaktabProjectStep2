package ir.maktab.maktabprojectstep2.repository;

import ir.maktab.maktabprojectstep2.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
