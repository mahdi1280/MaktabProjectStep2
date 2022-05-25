package ir.maktab.maktabprojectstep2.controller;

import ir.maktab.maktabprojectstep2.config.SecurityUtil;
import ir.maktab.maktabprojectstep2.dto.request.UnderServiceSaveRequest;
import ir.maktab.maktabprojectstep2.dto.response.UnderServiceResponse;
import ir.maktab.maktabprojectstep2.model.UnderService;
import ir.maktab.maktabprojectstep2.model.User;
import ir.maktab.maktabprojectstep2.service.underservice.UnderServiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/underService")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UnderServiceController {

    private final UnderServiceService underServiceService;

    public UnderServiceController(UnderServiceService underServiceService) {
        this.underServiceService = underServiceService;
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Long> save(@Valid @RequestBody UnderServiceSaveRequest underServiceSaveRequest){
        UnderService underService=underServiceService.save(underServiceSaveRequest);
        return ResponseEntity.ok(underService.getId());
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','EXPERT')")
    public ResponseEntity<List<UnderServiceResponse>> getAll(){
        List<UnderService> all = underServiceService.findAll();
        return ResponseEntity.ok(all.stream().map(this::createUnderServiceResponse).collect(Collectors.toList()));
    }

    @GetMapping("/expert")
    @PreAuthorize("hasAnyAuthority('EXPERT','ADMIN')")
    public ResponseEntity<List<UnderServiceResponse>> getAllExpert(){
        User currentUser = SecurityUtil.getCurrentUser();
        return ResponseEntity.ok(currentUser.getServices().stream().map(this::createUnderServiceResponse).collect(Collectors.toList()));
    }

    private UnderServiceResponse createUnderServiceResponse(UnderService underService) {
        return UnderServiceResponse.builder()
                .id(underService.getId())
                .title(underService.getDetails())
                .build();
    }
}
