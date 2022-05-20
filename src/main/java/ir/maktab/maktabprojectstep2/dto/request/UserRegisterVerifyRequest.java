package ir.maktab.maktabprojectstep2.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class UserRegisterVerifyRequest implements Serializable {

    private final Long id;
    private final String email;
    private final String verifyCode;

    @JsonCreator
    public UserRegisterVerifyRequest(Long id, String email, String verifyCode) {
        this.id = id;
        this.email = email;
        this.verifyCode = verifyCode;
    }

    @NotNull(message = "{user.register.id.null}")
    @Min(value = 1,message = "{user.register.id.min}")
    public Long getId() {
        return id;
    }

    @NotNull(message = "{user.register.email.null}")
    @NotBlank(message = "{user.register.email.blank}")
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "{user.register.email.pattern}")
    public String getEmail() {
        return email;
    }

    @NotNull(message = "{user.register.verify.code.null}")
    @NotBlank(message = "{user.register.verify.code.blank}")
    public String getVerifyCode() {
        return verifyCode;
    }
}
