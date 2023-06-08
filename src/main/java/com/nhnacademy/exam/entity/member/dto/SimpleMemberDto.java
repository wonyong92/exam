package com.nhnacademy.exam.entity.member.dto;

import com.nhnacademy.exam.entity.member.entity.DepartmentMember;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class SimpleMemberDto {

  Long id;
  String name;

  public SimpleMemberDto(DepartmentMember member) {
    this.id = member.getId();
    this.name = member.getName();
  }
}
