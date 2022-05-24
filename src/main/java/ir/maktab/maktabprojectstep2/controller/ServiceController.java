package ir.maktab.maktabprojectstep2.controller;

import ir.maktab.maktabprojectstep2.dto.request.ServiceSaveRequest;
import ir.maktab.maktabprojectstep2.dto.response.ServiceServiceResponse;
import ir.maktab.maktabprojectstep2.dto.response.UnderServiceResponse;
import ir.maktab.maktabprojectstep2.model.Service;
import ir.maktab.maktabprojectstep2.model.UnderService;
import ir.maktab.maktabprojectstep2.service.service.ServiceService;
import ir.maktab.maktabprojectstep2.service.underservice.UnderServiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Long> save(@Valid @RequestBody ServiceSaveRequest serviceSaveRequest){
        Service service=serviceService.save(serviceSaveRequest);
        return ResponseEntity.ok(service.getId());
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
