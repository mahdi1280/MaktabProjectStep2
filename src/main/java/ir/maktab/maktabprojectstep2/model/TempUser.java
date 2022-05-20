package ir.maktab.maktabprojectstep2.model;

import ir.maktab.maktabprojectstep2.model.enums.Role;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(schema = Schema.SCHEMA_NAME)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TempUser extends BaseEntity{

    private String firstname;
    private String lastname;
    private String password;
    private String email;
    private byte[] image;
    private Role role;
    private String verifyCode;
    private int tryCount;
    private LocalDateTime expireDate;
    @CreationTimestamp
    private LocalDateTime createdAt;


}
