package ir.maktab.maktabprojectstep2.repository;

import ir.maktab.maktabprojectstep2.model.TempUser;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TempUserRepository extends JpaRepository<TempUser,Long> {
    Optional<TempUser> findTopByEmail(String email, Sort sort);
}
