package com.nhnacademy.exam.exception.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@ToString
@Getter
public class ExceptionDto {

  String title;
  int status;

  LocalDateTime timestamp = LocalDateTime.now();

  public ExceptionDto(String title, HttpStatus status) {
    this.title = title;
    this.status = status.value();
  }
}
