package ir.maktab.maktabprojectstep2.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Builder
@Getter
public class ServiceServiceResponse implements Serializable {

    private final long id;
    private final String title;
    private final List<UnderServiceResponse> underServiceResponse;

}
