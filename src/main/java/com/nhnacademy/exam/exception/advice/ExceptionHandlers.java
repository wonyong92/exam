package com.nhnacademy.exam.exception.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.exam.exception.DepartmentExistsException;
import com.nhnacademy.exam.exception.DepartmentNotExistsException;
import com.nhnacademy.exam.exception.SimpleHttpMediaTypeException;
import com.nhnacademy.exam.exception.UsernameHeaderNotFound;
import com.nhnacademy.exam.exception.dto.ExceptionDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Component
public class ExceptionHandlers {

  private final ObjectMapper objectMapper;

  public ExceptionHandlers(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @ExceptionHandler({DepartmentExistsException.class, DepartmentNotExistsException.class, UsernameHeaderNotFound.class, MissingServletRequestParameterException.class, SimpleHttpMediaTypeException.class})
  public ResponseEntity<String> handleException(Exception ex) throws JsonProcessingException {
    ExceptionDto exceptionDto = createExceptionDto(ex);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    return buildResponse(exceptionDto, headers);
  }

  @ExceptionHandler(ClassNotFoundException.class)
  public ResponseEntity<String> handleMissingComponent(ClassNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
  }

  private ExceptionDto createExceptionDto(Exception ex) {
    if (ex instanceof DepartmentExistsException) {
      return new ExceptionDto("부서 아이디 중복: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    } else if (ex instanceof DepartmentNotExistsException) {
      return new ExceptionDto("Department not found: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    } else if (ex instanceof UsernameHeaderNotFound) {
      return new ExceptionDto("Unauthorized", HttpStatus.UNAUTHORIZED);
    } else if (ex instanceof MissingServletRequestParameterException) {
      return new ExceptionDto("Required request parameter " + ex.getMessage() + " for method parameter type String is not present", HttpStatus.BAD_REQUEST);
    } else if (ex instanceof SimpleHttpMediaTypeException) {
      return new ExceptionDto("Could not find acceptable representation", HttpStatus.BAD_REQUEST);
    } else {
      throw new IllegalArgumentException("Unhandled exception type: " + ex.getClass());
    }
  }

  private ResponseEntity<String> buildResponse(ExceptionDto exceptionDto, HttpHeaders headers) throws JsonProcessingException {
    String json = objectMapper.writeValueAsString(exceptionDto);
    return ResponseEntity
        .status(exceptionDto.getStatus())
        .headers(headers)
        .body(json);
  }
}
