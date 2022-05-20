package ir.maktab.maktabprojectstep2.model;

import ir.maktab.maktabprojectstep2.model.enums.Role;
import ir.maktab.maktabprojectstep2.model.enums.UserStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = Schema.USER_TABLE_NAME,schema = Schema.SCHEMA_NAME)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends BaseEntity {

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private boolean deleted = false;
    private byte[] image;
    @CreationTimestamp
    private LocalDateTime createdAt;
    private int credit;
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    @Enumerated(EnumType.STRING)
    private Role role;
    private Integer score;
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "user_under_service",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns ={@JoinColumn(name = "under_server_id")}
    )
    private Collection<UnderService> services=new HashSet<>();

}
