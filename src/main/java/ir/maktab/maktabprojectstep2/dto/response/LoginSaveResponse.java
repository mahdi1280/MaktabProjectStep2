package ir.maktab.maktabprojectstep2.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class LoginSaveResponse {

    private final long id;
    private final String token;

}
