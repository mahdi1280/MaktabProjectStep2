package ir.maktab.maktabprojectstep2.service.transaction;

import ir.maktab.maktabprojectstep2.model.Transaction;
import ir.maktab.maktabprojectstep2.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService{

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Optional<Transaction> findByOrderIdAndTrackId(String orderId, String trackId) {
        return transactionRepository.findByOrderIdAndTrackId(orderId,trackId);
    }

    @Override
    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }
}
