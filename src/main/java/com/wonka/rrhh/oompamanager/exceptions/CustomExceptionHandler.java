package com.wonka.rrhh.oompamanager.exceptions;

import com.wonka.rrhh.oompamanager.utils.ErrorsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {

        final HttpStatus status = HttpStatus.NOT_FOUND;
        final ErrorMessage errorMessage = new ErrorMessage(new Date(), status, ex.getMessage(), request.getDescription(false));
        log.error("Error in " + ex.getStackTrace()[0] + ": " + ex.getMessage());

        return new ResponseEntity<>(errorMessage, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalExceptions(Exception ex, WebRequest request) {

        final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        final ErrorMessage errorMessage = new ErrorMessage(new Date(), status, ex.getMessage(), request.getDescription(false));
        log.error("Unexpected error:", ex);

        return new ResponseEntity<>(errorMessage, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {

        final String message = ErrorsUtil.INSTANCE.getErrorMessage(ex.getBindingResult());
        final ErrorMessage errorMessage = new ErrorMessage(new Date(), status, message, request.getDescription(false));
        final String errorClass = ex.getParameter().getContainingClass().getCanonicalName();
        final String errorMethod = ex.getParameter().getExecutable().getName();
        log.error("Validation error in method '" + errorMethod + "' from " + errorClass + ":\n" + message);

        return new ResponseEntity<>(errorMessage, status);
    }
}
