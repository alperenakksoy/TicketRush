package com.example.ticketrush.exception;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private static final String TIMESTAMP = "timestamp";
    private static final String MESSAGE = "message";
    private static final String STATUS = "status";
    private static final String ERRORS = "errors";

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<@NonNull Map<String, Object>> handleBusinessException(BusinessException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put(TIMESTAMP, LocalDateTime.now());
        response.put(MESSAGE, ex.getMessage());
        response.put(STATUS, HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OptimisticLockingFailureException.class)
    public ResponseEntity<@NonNull Map<String, Object>> handleOptimisticLockingException() {
        Map<String, Object> response = new HashMap<>();
        response.put(TIMESTAMP, LocalDateTime.now());
        response.put(MESSAGE, "Üzgünüz! Seçtiğiniz koltuk işlem sırasında başkası tarafından alındı. Lütfen tekrar deneyin.");
        response.put(STATUS, HttpStatus.CONFLICT.value()); // 409 Conflict

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<@NonNull Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap<>();
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        response.put(TIMESTAMP, LocalDateTime.now());
        response.put(MESSAGE, "Validation failed");
        response.put(ERRORS, errors);
        response.put(STATUS, HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
        @ExceptionHandler(Exception.class)
        public ResponseEntity<@NonNull Map<String, Object>> handleGeneralException(Exception ex) {

            Map<String, Object> response = new HashMap<>();
            response.put(TIMESTAMP, LocalDateTime.now());
            response.put(MESSAGE, "Unexpected error: " + ex.getMessage());
            response.put(STATUS, HttpStatus.INTERNAL_SERVER_ERROR.value());

            log.error("An unexpected error occurred", ex);

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
}