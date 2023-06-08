package com.nhnacademy.exam.entity.member.entity;

import com.nhnacademy.exam.entity.DepartmentAndMembers;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "DEPARTMENT_MEMBER")
@ToString
@EqualsAndHashCode
public class DepartmentMember {

  @Id
  @Column(name = "DEPARTMENT_MEMBER_ID")
  private Long id;
  private String name;
  @OneToMany(mappedBy = "departmentMember")
  private Set<DepartmentAndMembers> departmentAndMembers = new HashSet<>();

  public DepartmentMember(Long id, String memberName) {
    this.id = id;
    this.name = memberName;
  }

}
