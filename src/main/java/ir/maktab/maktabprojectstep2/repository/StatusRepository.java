package ir.maktab.maktabprojectstep2.repository;

import ir.maktab.maktabprojectstep2.idpay.StatusType;
import ir.maktab.maktabprojectstep2.model.StatusTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<StatusTransaction, Long> {

    StatusTransaction getByCode(StatusType statusType);
}
