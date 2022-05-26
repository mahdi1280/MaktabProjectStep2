package ir.maktab.maktabprojectstep2.service.comment;

import ir.maktab.maktabprojectstep2.dto.request.CommentSaveRequest;
import ir.maktab.maktabprojectstep2.model.Comment;
import ir.maktab.maktabprojectstep2.model.Offer;
import ir.maktab.maktabprojectstep2.model.User;
import ir.maktab.maktabprojectstep2.repository.CommentRepository;
import ir.maktab.maktabprojectstep2.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public Comment save(CommentSaveRequest commentSaveRequest, Offer offer) {
        Comment comment=createComment(commentSaveRequest,offer);
        User user = offer.getUser();
        user.setScore(user.getScore()+commentSaveRequest.getScore());
        userRepository.save(user);
        return commentRepository.save(comment);
    }

    private Comment createComment(CommentSaveRequest commentSaveRequest, Offer offer) {
        return Comment.builder()
                .details(commentSaveRequest.getDetails())
                .offer(offer)
                .score(commentSaveRequest.getScore())
                .build();
    }

    @Override
    public Optional<Comment> findById(long id) {
        return commentRepository.findById(id);
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }
}
