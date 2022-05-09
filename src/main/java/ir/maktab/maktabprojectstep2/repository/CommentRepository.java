package ir.maktab.maktabprojectstep2.repository;

import ir.maktab.maktabprojectstep2.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
