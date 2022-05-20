package ir.maktab.maktabprojectstep2.config;

import ir.maktab.maktabprojectstep2.model.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    public static User getCurrentUser(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
