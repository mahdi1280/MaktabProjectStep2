package ir.maktab.maktabprojectstep2.service.temp;

import ir.maktab.maktabprojectstep2.dto.request.UserCustomerSaveRequest;
import ir.maktab.maktabprojectstep2.model.TempUser;

import java.util.Optional;

public interface TempService {

    Optional<TempUser> findByEmail(String email);

    TempUser saveAndSendEmail(UserCustomerSaveRequest userCustomerSaveRequest);
}
