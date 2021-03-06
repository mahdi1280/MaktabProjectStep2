package ir.maktab.maktabprojectstep2.controller;

import com.gd.core.ErrorMessage;
import com.gd.core.RuleException;
import ir.maktab.maktabprojectstep2.dto.request.ExpertSaveRequest;
import ir.maktab.maktabprojectstep2.dto.request.SetUnderServiceRequest;
import ir.maktab.maktabprojectstep2.dto.response.TempUserResponse;
import ir.maktab.maktabprojectstep2.model.TempUser;
import ir.maktab.maktabprojectstep2.model.UnderService;
import ir.maktab.maktabprojectstep2.model.User;
import ir.maktab.maktabprojectstep2.service.temp.TempService;
import ir.maktab.maktabprojectstep2.service.underservice.UnderServiceService;
import ir.maktab.maktabprojectstep2.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/expert")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ExpertController {

    private static final List<String> IMAGE_TYPE= Arrays.asList("jpeg","jpg","JPEG","JPG");
    private final UserService userService;
    private final TempService tempService;
    private final UnderServiceService underServiceService;
    public ExpertController(UserService userService, TempService tempService, UnderServiceService underServiceService) {
        this.userService = userService;
        this.tempService = tempService;
        this.underServiceService = underServiceService;
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

    @PutMapping("/setUnderService")
    @PreAuthorize("hasAnyAuthority('ADMIN','EXPERT')")
    public ResponseEntity<String> setUnderService(@Valid @RequestBody SetUnderServiceRequest setUnderServiceRequest){
        User user = userService.findById(setUnderServiceRequest.getUserId()).orElseThrow(() -> new RuleException(ErrorMessage.error("user.not.found")));
        List<UnderService> underServices=underServiceService.findAllByIds(setUnderServiceRequest.getIds());
        user.setServices(underServices);
        userService.save(user);
        return ResponseEntity.ok("ok");
    }

    private void checkImageType(MultipartFile multipartFile) {
        if(multipartFile.getOriginalFilename()==null)
            throw new RuleException(ErrorMessage.error("image.type.not.valid"));
        String[] split = multipartFile.getContentType().split("/");
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
