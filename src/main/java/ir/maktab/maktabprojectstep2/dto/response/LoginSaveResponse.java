package ir.maktab.maktabprojectstep2.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class LoginSaveResponse {

    private final long id;
    private final String token;

}
