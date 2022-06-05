package ir.maktab.maktabprojectstep2.idpay.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gd.core.ErrorMessage;
import com.gd.core.RuleException;
import feign.Response;
import feign.codec.ErrorDecoder;
import ir.maktab.maktabprojectstep2.idpay.response.ExceptionDTO;

import java.io.IOException;

public class CustomErrorDecoder implements ErrorDecoder {

    private final ObjectMapper mapper;

    public CustomErrorDecoder(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        ExceptionDTO exceptionDTO;
        try {
            exceptionDTO = mapper.readValue(response.body().asInputStream(), ExceptionDTO.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to process response body.", e);
        }
        return new RuleException(ErrorMessage.error("response.idPay.errorCode." + exceptionDTO.getErrorCode(), exceptionDTO.getErrorCode()));
    }
}