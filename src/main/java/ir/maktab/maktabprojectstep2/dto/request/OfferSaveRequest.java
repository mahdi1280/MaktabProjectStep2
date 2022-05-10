package ir.maktab.maktabprojectstep2.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

public class OfferSaveRequest implements Serializable {

    private final LocalDateTime periodOfTime;
    private final Integer proposedPrice;
    private final LocalDateTime startTime;
    private final Long orderId;

    @JsonCreator
    public OfferSaveRequest(LocalDateTime periodOfTime, Integer proposedPrice, LocalDateTime startTime, Long orderId) {
        this.periodOfTime = periodOfTime;
        this.proposedPrice = proposedPrice;
        this.startTime = startTime;
        this.orderId = orderId;
    }

    @NotNull(message = "{offer.save.request.periodOfTime.null}")
    public LocalDateTime getPeriodOfTime() {
        return periodOfTime;
    }

    @NotNull(message = "{offer.save.request.proposedPrice.null}")
    public Integer getProposedPrice() {
        return proposedPrice;
    }

    @NotNull(message = "{offer.save.request.startTime.null}")
    public LocalDateTime getStartTime() {
        return startTime;
    }

    @NotNull(message = "{offer.save.request.orderId.null}")
    public Long getOrderId() {
        return orderId;
    }
}
