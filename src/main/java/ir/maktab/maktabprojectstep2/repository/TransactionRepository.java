package ir.maktab.maktabprojectstep2.repository;

import ir.maktab.maktabprojectstep2.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    Optional<Transaction> findByOrderIdAndTrackId(String orderId, String trackId);
}
