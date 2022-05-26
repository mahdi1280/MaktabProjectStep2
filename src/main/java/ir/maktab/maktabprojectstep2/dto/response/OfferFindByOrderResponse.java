package ir.maktab.maktabprojectstep2.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class OfferFindByOrderResponse implements Serializable {

    private final long id;
    private final LocalDateTime periodOfTime;
    private final int proposedPrice;
    private final LocalDateTime createdAt;
    private final LocalDateTime startTime;
    private final long userId;
    private final boolean selected;
}
