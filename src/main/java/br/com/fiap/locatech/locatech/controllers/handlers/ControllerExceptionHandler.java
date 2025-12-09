package br.com.fiap.locatech.locatech.controllers.handlers;

import br.com.fiap.locatech.locatech.dtos.ResponseError;
import br.com.fiap.locatech.locatech.exceptions.EndDateGreaterThanStartDateException;
import br.com.fiap.locatech.locatech.exceptions.IsNotAvailableException;
import br.com.fiap.locatech.locatech.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseError> handleResourceNotFoundException(Exception ex) {
        List<String> fieldErrors = new ArrayList<>();
        fieldErrors.add(ex.getMessage());
        var status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status.value()).body(new ResponseError(fieldErrors, status, LocalDateTime.now()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var status = HttpStatus.BAD_REQUEST;
        List<String> fieldErrors = new ArrayList<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            fieldErrors.add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
        }
        return ResponseEntity.status(status.value()).body(new ResponseError(fieldErrors, status, LocalDateTime.now()));
    }

    @ExceptionHandler(IsNotAvailableException.class)
    public ResponseEntity<ResponseError> handleIsNotAvailable(IsNotAvailableException ex) {
        var status = HttpStatus.BAD_REQUEST;
        List<String> fieldErrors = new ArrayList<>();
        fieldErrors.add(ex.getMessage());
        return ResponseEntity.status(status.value()).body(new ResponseError(fieldErrors, status, LocalDateTime.now()));
    }

    @ExceptionHandler(EndDateGreaterThanStartDateException.class)
    public ResponseEntity<ResponseError> handleEndDateGreaterThanStartDate(EndDateGreaterThanStartDateException ex) {
        var status = HttpStatus.BAD_REQUEST;
        List<String> fieldErrors = new ArrayList<>();
        fieldErrors.add(ex.getMessage());
        return ResponseEntity.status(status.value()).body(new ResponseError(fieldErrors, status, LocalDateTime.now()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseError> handleException(Exception ex) {
        List<String> fieldErrors = new ArrayList<>();
        fieldErrors.add(ex.getMessage());
        var status = HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(status.value()).body(new ResponseError(fieldErrors, status, LocalDateTime.now()));
    }
}
