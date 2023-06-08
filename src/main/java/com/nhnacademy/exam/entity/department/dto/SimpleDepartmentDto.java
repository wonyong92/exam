package com.nhnacademy.exam.entity.department.dto;

import com.nhnacademy.exam.entity.department.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class SimpleDepartmentDto {

  String id;
  String name;

  public SimpleDepartmentDto(Department department) {
    this.id = department.getId();
    this.name = department.getName();
  }
}
