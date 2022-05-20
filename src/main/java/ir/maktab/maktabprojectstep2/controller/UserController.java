package ir.maktab.maktabprojectstep2.controller;

import ir.maktab.maktabprojectstep2.core.ErrorMessage;
import ir.maktab.maktabprojectstep2.core.RuleException;
import ir.maktab.maktabprojectstep2.dto.request.UserCustomerSaveRequest;
import ir.maktab.maktabprojectstep2.dto.response.TempUserResponse;
import ir.maktab.maktabprojectstep2.model.TempUser;
import ir.maktab.maktabprojectstep2.model.User;
import ir.maktab.maktabprojectstep2.service.temp.TempService;
import ir.maktab.maktabprojectstep2.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class UserController {

    private final UserService userService;
    private final TempService tempService;

    public UserController(UserService userService, TempService tempService) {
        this.userService = userService;
        this.tempService = tempService;
    }

    @PostMapping
    public ResponseEntity<TempUserResponse> save(@RequestBody @Valid UserCustomerSaveRequest userCustomerSaveRequest) {
        if (!Objects.equals(userCustomerSaveRequest.getPassword(), userCustomerSaveRequest.getRePassword())) {
            throw new RuleException(ErrorMessage.error("password.not.match"));
        }
        checkExistUser(userCustomerSaveRequest.getEmail());
        Optional<TempUser> findTempUser = tempService.findByEmail(userCustomerSaveRequest.getEmail());
        if (findTempUser.isPresent() && findTempUser.get().getExpireDate().isAfter(LocalDateTime.now()))
            return ResponseEntity.ok(createSaveUserResponse(findTempUser.get()));
        TempUser tempUser=tempService.saveAndSendEmail(userCustomerSaveRequest);
        return ResponseEntity.ok(createSaveUserResponse(tempUser));
    }

    private void checkExistUser(String email) {
        Optional<User> byEmail = userService.findByEmail(email);
        if (byEmail.isPresent())
            throw new RuleException(ErrorMessage.error("email.is.exist"));
    }

    private TempUserResponse createSaveUserResponse(TempUser tempUser) {
        return TempUserResponse.builder()
                .id(tempUser.getId())
                .email(tempUser.getEmail())
                .expireDate(tempUser.getExpireDate())
                .serverTime(LocalDateTime.now())
                .build();
    }
}
