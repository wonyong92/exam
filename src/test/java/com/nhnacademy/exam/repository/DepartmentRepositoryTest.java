package com.nhnacademy.exam.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.exam.ExamApplication;
import com.nhnacademy.exam.entity.department.entity.Department;
import com.nhnacademy.exam.parser.DepartmentParserResolver;
import com.nhnacademy.exam.repository.DepartmentRepository;
import java.util.NoSuchElementException;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;


@ActiveProfiles("dev")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = {ObjectMapper.class,com.nhnacademy.exam.parser.impl.CsvDepartmentParser.class,com.nhnacademy.exam.parser.impl.JsonDepartmentParser.class,com.nhnacademy.exam.parser.impl.TextDepartmentParser.class,DepartmentParserResolver.class,ExamApplication.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
class DepartmentRepositoryTest {

  @Autowired
  private DepartmentRepository departmentRepository;

  private Department department;

  @BeforeEach
  void setUp() {
    department = new Department("CS001", "CS1팀");
  }

  @Test
  @Order(1)
  void createDepartment() {
    Department savedDepartment = departmentRepository.save(department);
    assertThat(savedDepartment).isNotNull();
    assertThat(savedDepartment.getId()).isEqualTo(department.getId());
    assertThat(savedDepartment.getName()).isEqualTo(department.getName());
  }

  @Test
  @Order(2)
  void readDepartment() {
    Department foundDepartment = departmentRepository.findById(department.getId()).orElse(null);
    assertThat(foundDepartment).isNotNull();
    assertThat(foundDepartment.getId()).isEqualTo(department.getId());
    assertThat(foundDepartment.getName()).isEqualTo(department.getName());
  }

  @Test
  @Order(3)
  void updateDepartment() {
    String updatedName = "Updated CS1팀";
    department = new Department("CS001", updatedName);
    Department updatedDepartment = departmentRepository.save(department);
    assertThat(updatedDepartment).isNotNull();
    assertThat(updatedDepartment.getId()).isEqualTo(department.getId());
    assertThat(updatedDepartment.getName()).isEqualTo(updatedName);
  }

  @Test
  @Order(4)
  void deleteDepartment() {
    departmentRepository.deleteById(department.getId());
    Department deletedDepartment = departmentRepository.findById(department.getId()).orElse(null);
    assertThat(deletedDepartment).isNull();
  }

  @Test
  void departmentNotFoundException() {
    String nonExistentId = "CS0002";
    assertThatExceptionOfType(NoSuchElementException.class)
        .isThrownBy(() -> departmentRepository.findById(nonExistentId).orElseThrow());
  }
}


  /*
  디비 연결 확인
  환경이 dev인지 확인 - h2 드라이버 연결 확인
  초기화 데이터 삽입 성공 확인
  서비스 통합 테스트
  부서 삽입
  부서 읽기
  부서 업데이트
  부서 삭제

  부서 멤버 조회

  예외 처리

  * **/


