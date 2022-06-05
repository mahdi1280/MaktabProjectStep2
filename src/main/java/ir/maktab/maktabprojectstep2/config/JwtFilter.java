package ir.maktab.maktabprojectstep2.config;

import com.gd.core.ErrorMessage;
import com.gd.core.RuleException;
import ir.maktab.maktabprojectstep2.model.User;
import ir.maktab.maktabprojectstep2.repository.UserRepository;
import ir.maktab.maktabprojectstep2.service.jwt.JwtUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    public final JwtUtils jwtUtils;
    private final UserRepository userRepository;

    public JwtFilter(JwtUtils jwtUtils, UserRepository userRepository) {
        this.jwtUtils = jwtUtils;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = request.getHeader("Authorization");
        if (jwt != null) {
            String username = jwtUtils.getUsername(jwt);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                User byEmail = userRepository.findByEmail(username).orElseThrow(() -> new RuleException(ErrorMessage.error("user.not.found")));
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(byEmail, null, byEmail.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
