package com.nhnacademy.exam.entity.member.dto;

import com.nhnacademy.exam.entity.department.dto.SimpleDepartmentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class DepartMemberDto {

  SimpleDepartmentDto department;
  SimpleMemberDto employee;

}
