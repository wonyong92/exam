package com.nhnacademy.exam.entity.department.entity;

import com.nhnacademy.exam.entity.DepartmentAndMembers;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "DEPARTMENT")
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class Department {

  @Id
  @Column(name = "DEPARTMENT_ID")
  private String id;
  private String name;
  @OneToMany(mappedBy = "department")
  private Set<DepartmentAndMembers> departmentAndMembers = new HashSet<>();

  public Department(String id, String name) {
    this.id = id;
    this.name = name;
  }

}

