package com.nhnacademy.exam.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DepartmentAndMembersId implements Serializable {

  @Column(name = "department_id")
  private String departmentId;

  @Column(name = "member_id")
  private Long memberId;

}
