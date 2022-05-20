package ir.maktab.maktabprojectstep2.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Getter
public class UserCustomerSaveRequest implements Serializable {

    private final String username;
    private final String email;
    private final String password;
    private final String rePassword;

    @JsonCreator
    public UserCustomerSaveRequest(String username, String email, String password, String rePassword) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.rePassword = rePassword;
    }

    @NotNull(message = "{validation.user.username.notnull}")
    @NotBlank(message = "{validation.user.username.notblank}")
    @Pattern(regexp = "[a-zA-Z0-9?><;,{}\\-_+=!@#$%&*|']*", message = "{user.add.request.username.english}")
    public String getUsername() {
        return username;
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
}
