package com.app.learning.controller;

import com.app.learning.exception.ErrorResponse;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import java.io.PrintWriter;
import java.io.StringWriter;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Log4j2
public class RestErrorHandler extends ResponseEntityExceptionHandler {

  protected ResponseEntity<Object> handleHttpMessageNotReadable(
      final HttpMessageNotReadableException ex,
      final HttpHeaders headers,
      final HttpStatus status,
      final WebRequest request) {
    log.error(parseStackTrace(ex));
    final Throwable mostSpecificCause = ex.getMostSpecificCause();
    final ErrorResponse errorResponse;
    if (mostSpecificCause != null) {
      final String exceptionName = mostSpecificCause.getClass().getName();
      if (UnrecognizedPropertyException.class.getName().equals(exceptionName)) {
        UnrecognizedPropertyException cause = (UnrecognizedPropertyException) mostSpecificCause;
        errorResponse = new ErrorResponse("Unknown property " + cause.getPropertyName());
      } else {
        final String message = mostSpecificCause.getMessage();
        errorResponse =
            new ErrorResponse(
                String.format("ExceptionName: %s and Message: %s", exceptionName, message));
      }
    } else {
      errorResponse = new ErrorResponse(ex.getMessage());
    }
    return new ResponseEntity<>(errorResponse, headers, status);
  }

  private String parseStackTrace(final Exception e) {
    final StringWriter sw = new StringWriter();
    e.printStackTrace(new PrintWriter(sw));
    return sw.toString();
  }
}
