package vn.learning.jwt.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import vn.learning.jwt.domain.response.ResponseGlobal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ValidateService {

    public ResponseGlobal<Object> getErrorValidate(BindingResult result) {
        Map<String, String> errorValidates = new HashMap<>();
        List<FieldError> fieldErrors = result.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            errorValidates.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return ResponseGlobal.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Thất bại")
                .data(errorValidates)
                .build();
    }
}
