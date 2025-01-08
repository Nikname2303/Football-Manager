package com.example.manager.exception;

import jakarta.persistence.EntityNotFoundException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({SQLIntegrityConstraintViolationException.class})
    public ResponseEntity<Object> handleSqlIntegrityConstraintViolationException(
            SQLIntegrityConstraintViolationException exception) {
        String errorMessage = exception.getMessage();
        String timeStamp = LocalDateTime.now().toString();
        String responseMessage = errorMessage + " - " + timeStamp;
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(responseMessage);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException exception) {
        String errorMessage = exception.getMessage();
        String timeStamp = LocalDateTime.now().toString();
        String responseMessage = errorMessage + " - " + timeStamp;
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(responseMessage);
    }

    @ExceptionHandler({TransferException.class})
    public ResponseEntity<Object> handleTransferException(TransferException exception) {
        String errorMessage = exception.getMessage();
        String timeStamp = LocalDateTime.now().toString();
        String responseMessage = errorMessage + " - " + timeStamp;
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(responseMessage);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST);
        List<String> errors = ex.getBindingResult().getAllErrors().stream()
                .map(this::getErrorMessage)
                .toList();
        body.put("errors", errors);
        return new ResponseEntity<>(body, headers, status);
    }

    private String getErrorMessage(ObjectError e) {
        if (e instanceof FieldError fieldError) {
            String field = fieldError.getField();
            String message = fieldError.getDefaultMessage();
            return String.format("%s: %s", field, message);
        }
        return e.getDefaultMessage();
    }
}
