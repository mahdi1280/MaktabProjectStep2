package ir.maktab.maktabprojectstep2.controller;

import ir.maktab.maktabprojectstep2.core.ErrorMessage;
import ir.maktab.maktabprojectstep2.core.RuleException;
import ir.maktab.maktabprojectstep2.dto.request.CommentSaveRequest;
import ir.maktab.maktabprojectstep2.model.Comment;
import ir.maktab.maktabprojectstep2.model.Offer;
import ir.maktab.maktabprojectstep2.service.comment.CommentService;
import ir.maktab.maktabprojectstep2.service.offer.OfferService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/comment")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CommentController {

    private final CommentService commentService;
    private final OfferService offerService;

    public CommentController(CommentService commentService, OfferService offerService) {
        this.commentService = commentService;
        this.offerService = offerService;
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','CUSTOMER','EXPERT')")
    public ResponseEntity<Long> save(@Valid @RequestBody CommentSaveRequest commentSaveRequest){
        Offer offer = offerService.findById(commentSaveRequest.getOfferId()).orElseThrow(() -> new RuleException(ErrorMessage.error("offer.not.found")));
        Optional<Comment> byId = commentService.findById(offer.getId());
        if(byId.isPresent())
            throw new RuleException(ErrorMessage.error("comment.is.exist"));
        Comment save = commentService.save(commentSaveRequest, offer);
        return ResponseEntity.ok(save.getId());
    }

}
