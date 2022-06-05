package ir.maktab.maktabprojectstep2.service.status;

import ir.maktab.maktabprojectstep2.idpay.StatusType;
import ir.maktab.maktabprojectstep2.model.StatusTransaction;

public interface StatusService {

    StatusTransaction findByCode(StatusType code);
}
