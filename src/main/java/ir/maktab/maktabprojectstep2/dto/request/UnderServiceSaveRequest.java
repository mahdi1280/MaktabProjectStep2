package ir.maktab.maktabprojectstep2.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class UnderServiceSaveRequest implements Serializable {

    private final Long serviceId;
    private final String title;
    private final Integer basePrice;
    
    @JsonCreator
    public UnderServiceSaveRequest(Long serviceId, String title, Integer basePrice) {
        this.serviceId = serviceId;
        this.title = title;
        this.basePrice = basePrice;
    }

    @NotNull(message = "{under.service.save.id.not.null}")
    public Long getServiceId() {
        return serviceId;
    }

    @NotNull(message = "{under.service.save.title.nut.null}")
    @NotBlank(message = "{under.service.save.title.blank}")
    public String getTitle() {
        return title;
    }

    @NotNull(message = "{under.service.save.base.price.null}")
    @Min(value = 1,message = "{under.service.save.base.price.min}")
    public Integer getBasePrice() {
        return basePrice;
    }
}
