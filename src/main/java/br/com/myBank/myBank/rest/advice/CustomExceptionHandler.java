package br.com.myBank.myBank.rest.advice;

import br.com.myBank.myBank.exception.ErrorBadGatewayException;
import br.com.myBank.myBank.exception.ErrorBadRequestException;
import jar.presentation.representation.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ErrorBadRequestException.class)
    public ResponseEntity<ErrorResponse> erroBadRequestExceptionHandler(ErrorBadRequestException error) {

        ErrorResponse errorException = new ErrorResponse();

        errorException.setCode(HttpStatus.BAD_REQUEST.value());
        errorException.setDescription(error.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorException);
    }

    @ExceptionHandler(ErrorBadGatewayException.class)
    public ResponseEntity<ErrorResponse> erroBadGatewayExceptionHandler(ErrorBadGatewayException error) {

        ErrorResponse errorException = new ErrorResponse();

        errorException.setCode(HttpStatus.BAD_GATEWAY.value());
        errorException.setDescription(error.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(errorException);
    }
}
