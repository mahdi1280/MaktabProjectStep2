package ir.maktab.maktabprojectstep2.controller;

import ir.maktab.maktabprojectstep2.dto.request.UserSearchRequest;
import ir.maktab.maktabprojectstep2.dto.response.UserFindAllResponse;
import ir.maktab.maktabprojectstep2.model.User;
import ir.maktab.maktabprojectstep2.service.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Page<UserFindAllResponse>> getAll(UserSearchRequest userSearchRequest, Pageable pageable){
        Page<User> search = userService.search(userSearchRequest, pageable);
        return ResponseEntity.ok(search.map(this::createUserFindAllResponse));
    }

    private UserFindAllResponse createUserFindAllResponse(User user) {
        return UserFindAllResponse.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .role(user.getRole().get(0).toString())
                .score(user.getScore())
                .status(user.getStatus())
                .build();
    }


}
