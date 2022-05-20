package ir.maktab.maktabprojectstep2.controller;

import ir.maktab.maktabprojectstep2.core.ErrorMessage;
import ir.maktab.maktabprojectstep2.core.RuleException;
import ir.maktab.maktabprojectstep2.dto.request.UserCustomerSaveRequest;
import ir.maktab.maktabprojectstep2.dto.request.UserLoginRequest;
import ir.maktab.maktabprojectstep2.dto.request.UserRegisterVerifyRequest;
import ir.maktab.maktabprojectstep2.dto.response.LoginSaveResponse;
import ir.maktab.maktabprojectstep2.dto.response.TempUserResponse;
import ir.maktab.maktabprojectstep2.dto.response.UserCustomerSaveResponse;
import ir.maktab.maktabprojectstep2.model.TempUser;
import ir.maktab.maktabprojectstep2.model.User;
import ir.maktab.maktabprojectstep2.service.jwt.JwtUtils;
import ir.maktab.maktabprojectstep2.service.temp.TempService;
import ir.maktab.maktabprojectstep2.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager manager;
    private final JwtUtils jwtUtils;

    public UserController(UserService userService, TempService tempService, PasswordEncoder passwordEncoder, AuthenticationManager manager, JwtUtils jwtUtils) {
        this.userService = userService;
        this.tempService = tempService;
        this.passwordEncoder = passwordEncoder;
        this.manager = manager;
        this.jwtUtils = jwtUtils;
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

    @PostMapping("/verify")
    public ResponseEntity<UserCustomerSaveResponse> save(@Valid @RequestBody UserRegisterVerifyRequest userRegisterVerifyRequest) {
        checkExistUser(userRegisterVerifyRequest.getEmail());
        TempUser tempUser = tempService.findByEmail(userRegisterVerifyRequest.getEmail()).orElseThrow(() ->
                new RuleException(ErrorMessage.error("user.is.not.exist")));
        tempUser.setTryCount(tempUser.getTryCount() + 1);
        if (tempUser.getExpireDate().isBefore(LocalDateTime.now())) {
            throw new RuleException(ErrorMessage.error("verify.code.expire.date"));
        }
        if (!passwordEncoder.matches(userRegisterVerifyRequest.getVerifyCode(), tempUser.getVerifyCode())) {
            throw new RuleException(ErrorMessage.error("verify.code.not.valid"));
        }
        User responseUser = setUser(tempUser);
        userService.save(responseUser);
        String token = getToken(responseUser.getEmail(), tempUser.getPassword());
        return ResponseEntity.ok().body(new UserCustomerSaveResponse(responseUser.getId(), token));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginSaveResponse> save(@RequestBody @Valid UserLoginRequest request) {
        String token = getToken(request.getEmail(), request.getPassword());
        User user = userService.findByEmail(request.getEmail()).orElseThrow(()->new RuleException(ErrorMessage.error("user.not.found")));
        return ResponseEntity.ok(new LoginSaveResponse(user.getId(), token));
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

    private User setUser(TempUser tempUser) {
        return User.builder()
                .firstname(tempUser.getFirstname())
                .lastname(tempUser.getLastname())
                .password(passwordEncoder.encode(tempUser.getPassword()))
                .email(tempUser.getEmail())
                .build();
    }

    private String getToken(String email, String password) {
        try {
            manager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (Exception e) {
            throw new RuleException(ErrorMessage.error("user.not.found"));
        }
        return jwtUtils.generateToken(email);

    }
}
