package com.nhnacademy.exam.entity;

import com.nhnacademy.exam.entity.department.entity.Department;
import com.nhnacademy.exam.entity.member.entity.DepartmentMember;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "department_and_members")
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class DepartmentAndMembers {

  @EmbeddedId
  private DepartmentAndMembersId id;

  @ManyToOne
  @MapsId("departmentId")
  private Department department;

  @ManyToOne
  @MapsId("memberId")
  private DepartmentMember departmentMember;

}


