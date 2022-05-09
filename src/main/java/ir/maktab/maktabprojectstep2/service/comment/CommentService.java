package ir.maktab.maktabprojectstep2.service.comment;

import ir.maktab.maktabprojectstep2.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    void save(Comment comment);

    Optional<Comment> findById(long id);

    List<Comment> findAll();
}
