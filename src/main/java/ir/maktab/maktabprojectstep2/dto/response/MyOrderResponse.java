package ir.maktab.maktabprojectstep2.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class MyOrderResponse implements Serializable {

    private final long id;
    private final String address;
    private final int proposedPrice;
    private final LocalDateTime workTime;

}
