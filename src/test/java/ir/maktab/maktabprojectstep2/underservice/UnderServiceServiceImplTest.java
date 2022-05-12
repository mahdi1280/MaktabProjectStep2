package ir.maktab.maktabprojectstep2.underservice;

import ir.maktab.maktabprojectstep2.model.Service;
import ir.maktab.maktabprojectstep2.model.UnderService;
import ir.maktab.maktabprojectstep2.service.service.ServiceService;
import ir.maktab.maktabprojectstep2.service.underservice.UnderServiceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

@SpringBootTest
@DirtiesContext
class UnderServiceServiceImplTest {

    @Autowired
    UnderServiceService underServiceService;
    @Autowired
    ServiceService serviceService;

    @Test
    void save() {
        Service service = Service.builder()
                .title("manzel")
                .build();
        serviceService.save(service);
        UnderService underService = UnderService.builder()
                .details("asdasd")
                .service(service)
                .basePrice(123)
                .build();
        underServiceService.save(underService);
        Assertions.assertEquals(underService.getId(),underServiceService.findById(underService.getId()).get().getId());
    }

    @Test
    void findAll(){
        List<UnderService> all = underServiceService.findAll();
        Assertions.assertEquals(all.size(),underServiceService.findAll().size());
    }
}