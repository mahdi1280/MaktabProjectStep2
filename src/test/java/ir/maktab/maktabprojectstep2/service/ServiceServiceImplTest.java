package ir.maktab.maktabprojectstep2.service;

import ir.maktab.maktabprojectstep2.model.Service;
import ir.maktab.maktabprojectstep2.service.service.ServiceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ServiceServiceImplTest {

    @Autowired
    ServiceService serviceService;

    @Test
    void save(){
       Service service=Service.builder()
                .title("manzel")
                .build();
        serviceService.save(service);
        Assertions.assertEquals(service.getTitle(),serviceService.findById(service.getId()).get().getTitle());
    }

    @Test
    void findAll(){
        List<Service> all = serviceService.findAll();
        Assertions.assertEquals(all.size(),serviceService.findAll().size());
    }

}