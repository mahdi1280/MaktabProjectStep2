package ir.maktab.maktabprojectstep2.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class CommentSaveRequest implements Serializable {

    private final Integer score;
    private final String details;
    private final Long offerId;

    @JsonCreator
    public CommentSaveRequest(Integer score, String details, Long offerId) {
        this.score = score;
        this.details = details;
        this.offerId = offerId;
    }

    @NotNull(message = "{comment.save.score.null}")
    public Integer getScore() {
        return score;
    }

    @NotNull(message = "{comment.save.details.null}")
    @NotBlank(message = "{comment.save.details.blank}")
    public String getDetails() {
        return details;
    }

    @NotNull(message = "{comment.save.offer.null}")
    public Long getOfferId() {
        return offerId;
    }
}
