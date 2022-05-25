package ir.maktab.maktabprojectstep2.dto.request;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public class SetUnderServiceRequest implements Serializable {

    private final Long userId;
    private final List<Long> ids;

    public SetUnderServiceRequest(Long userId, List<Long> ids) {
        this.userId = userId;
        this.ids = ids;
    }

    @NotNull(message = "{user.id.not.null}")
    public Long getUserId() {
        return userId;
    }

    @NotNull(message = "{id.cant.null}")
    public List<Long> getIds() {
        return ids;
    }
}
