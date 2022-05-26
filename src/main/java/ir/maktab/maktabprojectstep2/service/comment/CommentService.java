package ir.maktab.maktabprojectstep2.service.comment;

import ir.maktab.maktabprojectstep2.dto.request.CommentSaveRequest;
import ir.maktab.maktabprojectstep2.model.Comment;
import ir.maktab.maktabprojectstep2.model.Offer;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    void save(Comment comment);

    Comment save(CommentSaveRequest commentSaveRequest, Offer offer);

    Optional<Comment> findById(long id);

    List<Comment> findAll();
}
