package com.nhnacademy.exam.exception;

import lombok.Getter;

@Getter
public class DepartmentNotExistsException extends RuntimeException {

  public DepartmentNotExistsException(String message) {
    super(message);
  }

}
