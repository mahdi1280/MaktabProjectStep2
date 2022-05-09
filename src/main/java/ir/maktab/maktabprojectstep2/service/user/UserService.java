package ir.maktab.maktabprojectstep2.service.user;

import ir.maktab.maktabprojectstep2.model.User;
import ir.maktab.maktabprojectstep2.request.UserSearchRequest;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void save(User user);

    Optional<User> findById(long id);

    List<User> findALlExpert();

    List<User> findAllUser();

    List<User> findAll();

    List<User> search(UserSearchRequest userSearchRequest);
}
