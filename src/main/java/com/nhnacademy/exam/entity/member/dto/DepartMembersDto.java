package com.nhnacademy.exam.entity.member.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DepartMembersDto {

  List<DepartMemberDto> list = new ArrayList<>();

  public void add(DepartMemberDto dto) {
    this.list.add(dto);
  }
}
