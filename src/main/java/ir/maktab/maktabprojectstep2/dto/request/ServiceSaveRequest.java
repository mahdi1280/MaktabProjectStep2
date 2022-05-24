package ir.maktab.maktabprojectstep2.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


public class ServiceSaveRequest implements Serializable {

    private final String title;

    @JsonCreator
    public ServiceSaveRequest(String title) {
        this.title = title;
    }

    @NotNull(message = "{service.save.request.not.null}")
    @NotBlank(message = "{service.save.request.not.blank}")
    public String getTitle() {
        return title;
    }
}
