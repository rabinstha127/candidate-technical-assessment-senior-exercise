package com.rabin.moviewiki.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class HttpErrorResponse extends HttpMessageResponse {
    private HttpStatus status;
    private String timestamp;

    public HttpErrorResponse(int code, String message) {
        super(code, message);
    }

    public HttpErrorResponse(int code, String message, HttpStatus status) {
        this(code, message);
        this.status = status;
        this.timestamp = LocalDateTime.now().toString();
    }
}
