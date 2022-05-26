package ir.maktab.maktabprojectstep2.dto.response;

import ir.maktab.maktabprojectstep2.model.Schema;
import ir.maktab.maktabprojectstep2.model.enums.Role;
import ir.maktab.maktabprojectstep2.model.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class UserFindAllResponse implements Serializable {

    private final long id;
    private final String firstname;
    private final String lastname;
    private final String email;
    private final String role;
    private final Integer score;
    private final UserStatus status;
    private final int credit;
}
