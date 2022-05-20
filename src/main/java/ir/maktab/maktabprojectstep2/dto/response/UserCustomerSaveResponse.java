package ir.maktab.maktabprojectstep2.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.Serializable;

@AllArgsConstructor
@Builder
public class UserCustomerSaveResponse implements Serializable {

    private final long id;
    private final String token;
}
