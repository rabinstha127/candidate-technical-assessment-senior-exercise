package com.rabin.moviewiki.exceptions;

import com.rabin.moviewiki.dto.HttpErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionController {

    @ExceptionHandler(NoDataException.class)
    public ResponseEntity<HttpErrorResponse> dataNotFoundException(final NoDataException ex) {
        final HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ex.printStackTrace();
        return ResponseEntity.badRequest().body(new HttpErrorResponse(badRequest.value(), ex.getMessage(), badRequest));
    }

}
