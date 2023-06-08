package com.nhnacademy.exam.entity;

import com.nhnacademy.exam.entity.department.entity.Department;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DepartmentTest {
  @Test
  void testConstructorAndGetters() {
    String id = "D001";
    String name = "테스트 부서";

    Department department = new Department(id, name);

    Assertions.assertEquals(id, department.getId());
    Assertions.assertEquals(name, department.getName());

    // departmentAndMembers는 초기화된 빈 Set인지 확인
    Assertions.assertNotNull(department.getDepartmentAndMembers());
    Assertions.assertTrue(department.getDepartmentAndMembers().isEmpty());
  }

  @Test
  void testSetters() {
    String id = "D001";
    String name = "테스트 부서";

    Department department = new Department(id,name);

    Assertions.assertEquals(id, department.getId());
    Assertions.assertEquals(name, department.getName());
  }

  @Test
  void testEquals() {
    String id1 = "D001";
    String name1 = "테스트 부서";

    String id2 = "D002";
    String name2 = "테스트 부서 2";

    Department department1 = new Department(id1, name1);
    Department department2 = new Department(id1, name1);  // 동일한 ID와 이름을 가진 다른 객체
    Department department3 = new Department(id2, name2);  // 다른 ID와 이름을 가진 객체

    Assertions.assertEquals(department1, department1);  // 동일한 객체
    Assertions.assertEquals(department1, department2);  // 동일한 ID와 이름을 가진 다른 객체
    Assertions.assertNotEquals(department1, department3);  // 다른 ID와 이름을 가진 객체
    Assertions.assertNotEquals(null,department1 );  // null과의 비교
    Assertions.assertNotEquals("Some Object",department1 );  // 다른 타입의 객체와의 비교
  }

  @Test
  void testToString(){
    String id = "D001";
    String name = "테스트 부서";

    Department department = new Department(id,name);

    Assertions.assertEquals("Department(id=D001, name=테스트 부서, departmentAndMembers=[])",department.toString() );
  }
}
