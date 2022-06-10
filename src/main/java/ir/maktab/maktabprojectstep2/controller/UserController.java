package ir.maktab.maktabprojectstep2.controller;

import com.gd.core.ErrorMessage;
import com.gd.core.RuleException;
import ir.maktab.maktabprojectstep2.config.SecurityUtil;
import ir.maktab.maktabprojectstep2.dto.request.UserSearchRequest;
import ir.maktab.maktabprojectstep2.dto.response.UserFindAllResponse;
import ir.maktab.maktabprojectstep2.model.User;
import ir.maktab.maktabprojectstep2.model.enums.UserStatus;
import ir.maktab.maktabprojectstep2.service.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/expert")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<List<UserFindAllResponse>> getAllExpert(){
        List<User> search = userService.findALlExpert();
        return ResponseEntity.ok(search.stream().map(this::createUserFindAllResponse).collect(Collectors.toList()));
    }

    @GetMapping("/me")
    @PreAuthorize("hasAnyAuthority('ADMIN','CUSTOMER','EXPERT')")
    public ResponseEntity<UserFindAllResponse> getMe(){
        User currentUser = SecurityUtil.getCurrentUser();
        return ResponseEntity.ok(createUserFindAllResponse(currentUser));
    }

    @PutMapping("/accept/{userId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<String> acceptUser(@PathVariable long userId){
        User user = userService.findById(userId).orElseThrow(() -> new RuleException(ErrorMessage.error("user.not.found")));
        user.setStatus(UserStatus.ACCEPT);
        userService.save(user);
        return ResponseEntity.ok("ok");
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
                .credit(user.getCredit())
                .build();
    }


}
