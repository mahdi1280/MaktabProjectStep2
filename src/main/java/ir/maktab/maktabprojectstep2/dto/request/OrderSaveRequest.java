package ir.maktab.maktabprojectstep2.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

public class OrderSaveRequest implements Serializable {

    private final Integer proposedPrice;
    private final String address;
    private final LocalDateTime workTime;
    private final Long underServiceId;

    @JsonCreator
    public OrderSaveRequest(Integer proposedPrice, String address, LocalDateTime workTime, Long underServiceId) {
        this.proposedPrice = proposedPrice;
        this.address = address;
        this.workTime = workTime;
        this.underServiceId = underServiceId;
    }

    @NotNull(message = "{order.save.request.proposedPrice.null}")
    public Integer getProposedPrice() {
        return proposedPrice;
    }

    @NotBlank(message = "{order.save.request.address.blank}")
    public String getAddress() {
        return address;
    }

    @NotNull(message = "{order.save.request.workTime.null}")
    public LocalDateTime getWorkTime() {
        return workTime;
    }

    @NotNull(message = "{order.save.request.underServiceId.null}")
    public Long getUnderServiceId() {
        return underServiceId;
    }
}
