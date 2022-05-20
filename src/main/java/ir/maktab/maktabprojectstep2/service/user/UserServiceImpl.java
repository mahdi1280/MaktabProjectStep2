package ir.maktab.maktabprojectstep2.service.user;

import ir.maktab.maktabprojectstep2.model.User;
import ir.maktab.maktabprojectstep2.model.enums.Role;
import ir.maktab.maktabprojectstep2.repository.UserRepository;
import ir.maktab.maktabprojectstep2.dto.request.UserSearchRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(User user) {
        this.userRepository.save(user);
    }

    @Override
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findALlExpert() {
        return userRepository.findAllByRole(Role.EXPERT);
    }

    @Override
    public List<User> findAllUser() {
        return userRepository.findAllByRole(Role.CUSTOMER);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> search(UserSearchRequest userSearchRequest) {
        return userRepository.findAll(new UserSearch(userSearchRequest));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
