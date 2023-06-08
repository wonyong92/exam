package com.nhnacademy.exam.entity;

import com.nhnacademy.exam.entity.member.entity.DepartmentMember;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DepartmentMemberTest {

  @Test
  void testConstructor() {
    Long id = 20202201L;
    String name = "김이름";
    DepartmentMember departmentMember = new DepartmentMember(id,name);

    Assertions.assertEquals(id, departmentMember.getId());
    Assertions.assertEquals(name, departmentMember.getName());

    Set<DepartmentAndMembers> departmentAndMembers = departmentMember.getDepartmentAndMembers();
    Assertions.assertNotNull(departmentAndMembers);
    Assertions.assertTrue(departmentAndMembers.isEmpty());
  }

  @Test
  void testGetterSetter() {
    Long id = 20202201L;
    String name = "김이름";
    DepartmentMember departmentMember = new DepartmentMember(id,name);

    Assertions.assertEquals(id, departmentMember.getId());
    Assertions.assertEquals(name, departmentMember.getName());
  }


  @Test
  void testEquals() {
    Long id = 20202201L;
    String name = "김이름";
    DepartmentMember departmentMember = new DepartmentMember(id,name);

    Long id2 = 20230501L;
    String name2 = "최상수";
    DepartmentMember departmentMember2 = new DepartmentMember(id2,name2);

    // 동일한 멤버
    DepartmentMember member1Copy = new DepartmentMember(id, name);

    // 같은 ID, 다른 이름을 가진 멤버
    DepartmentMember memberWithDifferentName = new DepartmentMember(id, "김이름2");

    // 같은 이름, 다른 ID를 가진 멤버
    DepartmentMember memberWithDifferentId = new DepartmentMember(20202200L, name);

    // null이 아닌 다른 객체와의 비교
    String someObject = "Some Object";

    Assertions.assertEquals(departmentMember, departmentMember);  // 동일한 객체
    Assertions.assertEquals(departmentMember, member1Copy);  // 같은 ID, 같은 이름을 가진 멤버
    Assertions.assertNotEquals(departmentMember, departmentMember2);  // 다른 ID, 다른 이름을 가진 멤버
    Assertions.assertNotEquals(departmentMember, memberWithDifferentName);  // 같은 ID, 다른 이름을 가진 멤버
    Assertions.assertNotEquals(departmentMember, memberWithDifferentId);  // 같은 이름, 다른 ID를 가진 멤버
    Assertions.assertNotEquals(null,departmentMember );  // null과의 비교
    Assertions.assertNotEquals(departmentMember, someObject);  // 다른 타입의 객체와의 비교
  }
}


