package ir.maktab.maktabprojectstep2.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;


public class UserLoginRequest implements Serializable {

    private final String email;
    private final String password;

    @JsonCreator
    public UserLoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @NotNull(message = "{user.login.email.null}")
    @NotBlank(message = "{user.login.email.blank}")
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "{user.login.email.not.valid}")
    public String getEmail() {
        return email;
    }

    @NotNull(message = "{user.login.password.null}")
    @NotBlank(message = "{user.login.password.Blank}")
    public String getPassword() {
        return password;
    }
}
