package ir.maktab.maktabprojectstep2.controller;

import com.gd.core.ErrorMessage;
import com.gd.core.RuleException;
import com.gd.core.ValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class DefaultExceptionHandlerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultExceptionHandlerTest.class);
    private final MessageSourceAccessor messageSourceAccessor;
    private final String maxSize;

    public DefaultExceptionHandlerTest(MessageSourceAccessor messageSourceAccessor, @Value("${spring.servlet.multipart.max-file-size}") String maxSize) {
        this.messageSourceAccessor = messageSourceAccessor;
        this.maxSize=maxSize;
    }

    @ExceptionHandler(RuleException.class)
    public ResponseEntity<List<ErrorMessage>> ruleExceptionHandler(RuleException ruleException) {
        List<ErrorMessage> errorMessages = new ArrayList<>();
        for (ErrorMessage errorMessage : ruleException.getErrorMessages()) {
            errorMessages.add(ErrorMessage.error(messageSourceAccessor.getMessage(errorMessage.getMessage()), errorMessage.getCode()));
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessages);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<List<ErrorMessage>> exceptionHandler(Exception e) {
        LOGGER.error("exception: ", e);
        List<ErrorMessage> error = Collections.singletonList(ErrorMessage.error(e.getMessage()));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<List<ValidationError>> bindExceptionHandler(BindException e) {
        List<ValidationError> validationErrors = new ArrayList<>();
        for (FieldError fieldError : e.getFieldErrors()) {
            ValidationError validationError = ValidationError.builder()
                    .setCode(fieldError.getCode())
                    .setDefaultMessage(fieldError.getDefaultMessage())
                    .setField(fieldError.getObjectName())
                    .setRejectedValue(String.valueOf(fieldError.getRejectedValue()))
                    .build();
            validationErrors.add(validationError);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationErrors);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<List<ErrorMessage>> maxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        List<ErrorMessage> error = Collections.singletonList(ErrorMessage.error(this.messageSourceAccessor.getMessage("upload.image.max.file.size", new Object[]{maxSize}), e.getMessage()));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }


}
