package ir.maktab.maktabprojectstep2.service.user;

import ir.maktab.maktabprojectstep2.dto.request.UserSearchRequest;
import ir.maktab.maktabprojectstep2.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void save(User user);

    Optional<User> findById(long id);

    List<User> findALlExpert();

    List<User> findAllUser();

    List<User> findAll();

    Page<User> search(UserSearchRequest userSearchRequest, Pageable pageable);

    Optional<User> findByEmail(String email);
}
