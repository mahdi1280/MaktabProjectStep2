package ir.maktab.maktabprojectstep2.model.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    CUSTOMER,
    EXPERT,
    ADMIN;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
