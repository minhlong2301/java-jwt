package vn.learning.jwt.domain.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseGlobal<T> {

    private Integer status;

    private String message;

    private T data;
}
