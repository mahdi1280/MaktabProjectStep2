package ir.maktab.maktabprojectstep2.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Builder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
public class OrderSaveRequest implements Serializable {

    private final Integer proposedPrice;
    private final String address;
    private final LocalDateTime workTime;
    private final Long underServiceId;
    private final Double latitute;
    private final Double longitute;

    @JsonCreator
    public OrderSaveRequest(Integer proposedPrice, String address, LocalDateTime workTime, Long underServiceId, Double latitute, Double longitute) {
        this.proposedPrice = proposedPrice;
        this.address = address;
        this.workTime = workTime;
        this.underServiceId = underServiceId;
        this.latitute = latitute;
        this.longitute = longitute;
    }

    @NotNull(message = "{order.save.request.proposedPrice.null}")
    @Min(value = 1, message = "{order.save.request.proposedPrice.min}")
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
    @Min(value = 1, message = "{order.save.request.underServiceId.min}")
    public Long getUnderServiceId() {
        return underServiceId;
    }

    public Double getLatitute() {
        return latitute;
    }

    public Double getLongitute() {
        return longitute;
    }
}
