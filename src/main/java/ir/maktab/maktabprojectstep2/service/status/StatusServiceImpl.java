package ir.maktab.maktabprojectstep2.service.status;

import ir.maktab.maktabprojectstep2.idpay.StatusType;
import ir.maktab.maktabprojectstep2.model.StatusTransaction;
import ir.maktab.maktabprojectstep2.repository.StatusRepository;
import org.springframework.stereotype.Service;

@Service
public class StatusServiceImpl implements StatusService {

    private final StatusRepository statusRepository;

    public StatusServiceImpl(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }


    @Override
    public StatusTransaction findByCode(StatusType code) {
        return statusRepository.getByCode(code);
    }
}
