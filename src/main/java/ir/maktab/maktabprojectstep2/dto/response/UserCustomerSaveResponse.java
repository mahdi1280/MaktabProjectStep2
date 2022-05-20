package ir.maktab.maktabprojectstep2.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Builder
@Getter
public class UserCustomerSaveResponse implements Serializable {

    private final long id;
    private final String token;
}
