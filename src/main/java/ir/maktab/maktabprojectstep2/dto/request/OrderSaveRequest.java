package ir.maktab.maktabprojectstep2.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.Min;
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

    public static Builder builder() {
        return new Builder();
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

    public static class Builder {

        private Integer proposedPrice;
        private String address;
        private LocalDateTime workTime;
        private Long underServiceId;

        private Builder() {
        }

        public Builder proposedPrice(Integer proposedPrice) {
            this.proposedPrice = proposedPrice;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder workTime(LocalDateTime workTime) {
            this.workTime = workTime;
            return this;
        }

        public Builder underServiceId(Long underServiceId) {
            this.underServiceId = underServiceId;
            return this;
        }

        public OrderSaveRequest build() {
            return new OrderSaveRequest(proposedPrice, address, workTime, underServiceId);
        }
    }
}
