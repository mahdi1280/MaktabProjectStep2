package ir.maktab.maktabprojectstep2.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class ExpertSaveRequest {

    private final String firstname;
    private final String lastname;
    private final String email;
    private final String password;
    private final String rePassword;
    private final MultipartFile multipartFile;

    @JsonCreator
    public ExpertSaveRequest(String firstname, String lastname, String email, String password, String rePassword, MultipartFile multipartFile) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.rePassword = rePassword;
        this.multipartFile = multipartFile;
    }

    @NotNull(message = "{validation.user.username.notnull}")
    @NotBlank(message = "{validation.user.username.notblank}")
    public String getFirstname() {
        return firstname;
    }

    @NotNull(message = "{validation.user.lastname.notnull}")
    @NotBlank(message = "{validation.user.lastname.notblank}")
    public String getLastname() {
        return lastname;
    }

    @NotNull(message = "{validation.user.email.notnull}")
    @NotBlank(message = "{validation.user.email.notblank}")
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "{validation.user.email.pattern}")
    public String getEmail() {
        return email;
    }

    @NotNull(message = "{validation.user.password.notnull}")
    @NotBlank(message = "{validation.user.password.notblank}")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",message = "{user.add.request.password}")
    public String getPassword() {
        return password;
    }

    @NotNull(message = "{validation.user.repassword.notnull}")
    @NotBlank(message = "{validation.user.repassword.notblank}")
    public String getRePassword() {
        return rePassword;
    }

    @NotNull(message = "{image.is.null}")
    public MultipartFile getMultipartFile() {
        return multipartFile;
    }
}
