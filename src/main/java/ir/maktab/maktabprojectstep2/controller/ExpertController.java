package ir.maktab.maktabprojectstep2.controller;

import ir.maktab.maktabprojectstep2.core.ErrorMessage;
import ir.maktab.maktabprojectstep2.core.RuleException;
import ir.maktab.maktabprojectstep2.dto.request.ExpertSaveRequest;
import ir.maktab.maktabprojectstep2.dto.request.UserCustomerSaveRequest;
import ir.maktab.maktabprojectstep2.dto.response.TempUserResponse;
import ir.maktab.maktabprojectstep2.model.TempUser;
import ir.maktab.maktabprojectstep2.model.User;
import ir.maktab.maktabprojectstep2.service.jwt.JwtUtils;
import ir.maktab.maktabprojectstep2.service.temp.TempService;
import ir.maktab.maktabprojectstep2.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/expert")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ExpertController {

    private static final List<String> IMAGE_TYPE= Arrays.asList("jpeg","jpg","JPEG","JPG");
    private final UserService userService;
    private final TempService tempService;

    public ExpertController(UserService userService, TempService tempService) {
        this.userService = userService;
        this.tempService = tempService;
    }

    @PostMapping
    public ResponseEntity<TempUserResponse> save(@ModelAttribute @Valid ExpertSaveRequest expertSaveRequest) {
        if (!Objects.equals(expertSaveRequest.getPassword(), expertSaveRequest.getRePassword())) {
            throw new RuleException(ErrorMessage.error("password.not.match"));
        }
        checkImageType(expertSaveRequest.getMultipartFile());

        checkExistUser(expertSaveRequest.getEmail());
        Optional<TempUser> findTempUser = tempService.findByEmail(expertSaveRequest.getEmail());
        if (findTempUser.isPresent() && findTempUser.get().getExpireDate().isAfter(LocalDateTime.now()))
            return ResponseEntity.ok(createSaveUserResponse(findTempUser.get()));
        TempUser tempUser=tempService.saveAndSendEmail(expertSaveRequest);
        return ResponseEntity.ok(createSaveUserResponse(tempUser));
    }

    private void checkImageType(MultipartFile multipartFile) {
        if(multipartFile.getOriginalFilename()==null)
            throw new RuleException(ErrorMessage.error("image.type.not.valid"));
        String[] split = multipartFile.getOriginalFilename().split("/");
        if(split.length<=1)
            throw new RuleException(ErrorMessage.error("image.type.not.valid"));
        if(!IMAGE_TYPE.contains(split[1]))
            throw new RuleException(ErrorMessage.error("image.type.not.valid"));
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
