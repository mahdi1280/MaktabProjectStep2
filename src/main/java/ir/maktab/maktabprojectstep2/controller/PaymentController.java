package ir.maktab.maktabprojectstep2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PaymentController {

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','CUSTOMER','EXPERT')")
    public ResponseEntity<?> payment(){
        return ResponseEntity.ok().build();
    }
}
