package ir.maktab.maktabprojectstep2.controller;

import ir.maktab.maktabprojectstep2.dto.response.ServiceServiceResponse;
import ir.maktab.maktabprojectstep2.dto.response.UnderServiceResponse;
import ir.maktab.maktabprojectstep2.model.Service;
import ir.maktab.maktabprojectstep2.model.UnderService;
import ir.maktab.maktabprojectstep2.service.service.ServiceService;
import ir.maktab.maktabprojectstep2.service.underservice.UnderServiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/service")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ServiceController {

    private final ServiceService serviceService;
    private final UnderServiceService underServiceService;

    public ServiceController(ServiceService serviceService, UnderServiceService underServiceService) {
        this.serviceService = serviceService;
        this.underServiceService = underServiceService;
    }

    @GetMapping
    public ResponseEntity<List<ServiceServiceResponse>> getAll(){
        List<Service> all = serviceService.findAll();
        return ResponseEntity.ok(all.stream().map(this::createServiceResponse).collect(Collectors.toList()));
    }

    private ServiceServiceResponse createServiceResponse(Service service) {
        return ServiceServiceResponse.builder()
                .id(service.getId())
                .title(service.getTitle())
                .underServiceResponse(underServiceService.findByService(service).stream().map(this::createUnderServiceResponse).collect(Collectors.toList()))
                .build();
    }

    private UnderServiceResponse createUnderServiceResponse(UnderService underService) {
        return UnderServiceResponse.builder()
                .id(underService.getId())
                .title(underService.getDetails())
                .build();
    }
}
