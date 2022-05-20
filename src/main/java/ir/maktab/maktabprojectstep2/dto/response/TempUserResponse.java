package ir.maktab.maktabprojectstep2.dto.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
public class TempUserResponse implements Serializable {

    private final long id;
    private final String email;
    private final LocalDateTime expireDate;
    private final LocalDateTime serverTime;

    @JsonCreator
    public TempUserResponse(long id, String email, LocalDateTime expireDate, LocalDateTime serverTime) {
        this.id = id;
        this.email = email;
        this.expireDate = expireDate;
        this.serverTime = serverTime;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    public LocalDateTime getServerTime() {
        return serverTime;
    }
}
