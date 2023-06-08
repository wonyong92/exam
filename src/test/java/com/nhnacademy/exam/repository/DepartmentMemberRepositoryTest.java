package com.nhnacademy.exam.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.exam.ExamApplication;
import com.nhnacademy.exam.entity.member.entity.DepartmentMember;
import com.nhnacademy.exam.parser.DepartmentParserResolver;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ActiveProfiles("dev")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = {ObjectMapper.class,com.nhnacademy.exam.parser.impl.CsvDepartmentParser.class,com.nhnacademy.exam.parser.impl.JsonDepartmentParser.class,com.nhnacademy.exam.parser.impl.TextDepartmentParser.class, DepartmentParserResolver.class, ExamApplication.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class DepartmentMemberRepositoryTest {

  @Autowired
  private DepartmentMemberRepository departmentMemberRepository;

  private DepartmentMember departmentMember;

  @BeforeEach
  void setUp() {
    departmentMember = new DepartmentMember(20202201L, "김이름");
  }

  @Test
  @Order(1)
  void createDepartmentMember() {
    DepartmentMember savedMember = departmentMemberRepository.save(departmentMember);
    assertThat(savedMember).isNotNull();
    assertThat(savedMember.getId()).isEqualTo(departmentMember.getId());
    assertThat(savedMember.getName()).isEqualTo(departmentMember.getName());
  }

  @Test
  @Order(2)
  void readDepartmentMember() {
    DepartmentMember foundMember = departmentMemberRepository.findById(departmentMember.getId()).orElse(null);
    assertThat(foundMember).isNotNull();
    assertThat(foundMember.getId()).isEqualTo(departmentMember.getId());
    assertThat(foundMember.getName()).isEqualTo(departmentMember.getName());
  }

  @Test
  @Order(3)
  void updateDepartmentMember() {
    String updatedName = "Updated John Doe";
    departmentMember = new DepartmentMember(1L, updatedName);
    DepartmentMember updatedMember = departmentMemberRepository.save(departmentMember);
    assertThat(updatedMember).isNotNull();
    assertThat(updatedMember.getId()).isEqualTo(departmentMember.getId());
    assertThat(updatedMember.getName()).isEqualTo(updatedName);
  }

  @Test
  @Order(4)
  void deleteDepartmentMember() {
    departmentMemberRepository.deleteById(departmentMember.getId());
    DepartmentMember deletedMember = departmentMemberRepository.findById(departmentMember.getId()).orElse(null);
    assertThat(deletedMember).isNull();
  }

  @Test
  void departmentMemberNotFoundException() {
    Long nonExistentId = 999L;
    assertThatExceptionOfType(NoSuchElementException.class)
        .isThrownBy(() -> departmentMemberRepository.findById(nonExistentId).orElseThrow());
  }

}
