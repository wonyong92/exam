package com.nhnacademy.exam.service;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.nhnacademy.exam.entity.DepartmentAndMembers;
import com.nhnacademy.exam.entity.DepartmentAndMembersId;
import com.nhnacademy.exam.entity.department.dto.SimpleDepartmentDto;
import com.nhnacademy.exam.entity.department.entity.Department;
import com.nhnacademy.exam.entity.member.dto.DepartMembersDto;
import com.nhnacademy.exam.entity.member.entity.DepartmentMember;
import com.nhnacademy.exam.exception.DepartmentExistsException;
import com.nhnacademy.exam.exception.DepartmentNotExistsException;
import com.nhnacademy.exam.repository.DepartmentMemberRepository;
import com.nhnacademy.exam.repository.DepartmentRepository;
import com.nhnacademy.exam.service.department.DepartmentService;
import com.nhnacademy.exam.service.member.DepartmentMemberService;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class ServiceTest {

  @MockBean
  private DepartmentRepository departmentRepository;

  @MockBean
  private DepartmentMemberRepository departmentMemberRepository;
  private DepartmentService departmentService;
  private DepartmentMemberService departmentMemberService;
  SimpleDepartmentDto dto;
  Department department;

  @BeforeEach
  void setUp() {
    department = new Department("DEP001", "테스트용부서");
    dto = new SimpleDepartmentDto("DEP001", "Updated 테스트용부서");
    departmentService = new DepartmentService(departmentRepository);
    departmentMemberService = new DepartmentMemberService(departmentMemberRepository, departmentRepository);
    given(departmentRepository.findById(anyString())).willReturn(Optional.of(department));
    given(departmentRepository.save(any(Department.class))).willReturn(new Department("DEP001", "테스트용부서"));
  }

  @Test
  void testAddDepartment() {
    SimpleDepartmentDto dto = new SimpleDepartmentDto("DEP001", "테스트용부서");
    given(departmentRepository.findById(anyString())).willReturn(Optional.empty());
    String departmentId = departmentService.addDepartment(dto);
    assertThat(departmentId).isEqualTo("DEP001");
  }

  @Test
  void testGetDepartmentDto() {

    SimpleDepartmentDto dto = departmentService.getDepartmentDto("DEP001");
    assertThat(dto).isNotNull();
    assertThat(dto.getId()).isEqualTo("DEP001");
  }

  @Test
  void testUpdateDepartment() {

    departmentService.updateDepartment("DEP001", dto);
    verify(departmentRepository).save(any(Department.class));
  }

  @Test
  void testDeleteDepartment() {

    boolean deleted = departmentService.deleteDepartment("DEP001");
    assertThat(deleted).isTrue();
  }

  @Test
  void testDepartmentNotExistsException() {
    given(departmentRepository.findById(anyString())).willReturn(Optional.empty());
    assertThatExceptionOfType(DepartmentNotExistsException.class)
        .isThrownBy(() -> departmentService.getDepartmentDto("DEP001"));
  }

  @Test
  void testDepartmentExistsException() {
    assertThatExceptionOfType(DepartmentExistsException.class)
        .isThrownBy(() -> departmentService.addDepartment(new SimpleDepartmentDto("DEP001", "New Department")));
  }

  @Test
  void testGetDepartmentMembers() {

    DepartmentMember member1 = new DepartmentMember(1L, "Member 1");
    DepartmentMember member2 = new DepartmentMember(2L, "Member 2");

    department.getDepartmentAndMembers().add(new DepartmentAndMembers(new DepartmentAndMembersId(department.getId(),member1.getId()),department, member1));
    department.getDepartmentAndMembers().add(new DepartmentAndMembers(new DepartmentAndMembersId(department.getId(),member2.getId()),department, member2));



    DepartMembersDto membersDto = departmentMemberService.getDepartmentMembers(department.getId());

    assertThat(membersDto.getList().size()).isEqualTo(2);
  }

}
