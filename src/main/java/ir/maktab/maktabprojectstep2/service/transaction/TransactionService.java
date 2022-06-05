package ir.maktab.maktabprojectstep2.service.transaction;

import ir.maktab.maktabprojectstep2.model.Transaction;

import java.util.Optional;

public interface TransactionService {

    Optional<Transaction> findByOrderIdAndTrackId(String orderId, String trackId);

    void save(Transaction transaction);
}
