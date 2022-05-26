package ir.maktab.maktabprojectstep2.repository;

import ir.maktab.maktabprojectstep2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    @Query(nativeQuery = true,value = "select * from testtest.application_user u where (select ur.role from testtest.user_role ur where ur.user_email=u.email )=:role ")
    List<User> findAllByRole(String role);

    Optional<User> findByEmail(String email);
}
