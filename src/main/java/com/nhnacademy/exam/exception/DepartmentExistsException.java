package com.nhnacademy.exam.exception;

import lombok.Getter;

@Getter
public class DepartmentExistsException extends RuntimeException {
   public DepartmentExistsException(String message) {
    super(message);
  }
}
