package ir.maktab.maktabprojectstep2.controller;

import ir.maktab.maktabprojectstep2.dto.request.UnderServiceSaveRequest;
import ir.maktab.maktabprojectstep2.model.UnderService;
import ir.maktab.maktabprojectstep2.service.underservice.UnderServiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/underService")
public class UnderServiceController {

    private final UnderServiceService underServiceService;

    public UnderServiceController(UnderServiceService underServiceService) {
        this.underServiceService = underServiceService;
    }

    @PostMapping
    public ResponseEntity<Long> save(@Valid @RequestBody UnderServiceSaveRequest underServiceSaveRequest){
        UnderService underService=underServiceService.save(underServiceSaveRequest);
        return ResponseEntity.ok(underService.getId());
    }
}
