package ir.maktab.maktabprojectstep2.model;

import ir.maktab.maktabprojectstep2.model.enums.Role;
import ir.maktab.maktabprojectstep2.model.enums.UserStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Entity
@Table(name = Schema.USER_TABLE_NAME,schema = Schema.SCHEMA_NAME)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends BaseEntity implements UserDetails {

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

    @ElementCollection(targetClass = Role.class,fetch = FetchType.EAGER)
    @CollectionTable(schema = Schema.SCHEMA_NAME,name = "user_role", joinColumns = @JoinColumn(name = "user_email",referencedColumnName = "email"))
    @Enumerated(EnumType.STRING)
    private List<Role> role;
    private Integer score;
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "user_under_service",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns ={@JoinColumn(name = "under_server_id")}
    )
    private Collection<UnderService> services=new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role;
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
