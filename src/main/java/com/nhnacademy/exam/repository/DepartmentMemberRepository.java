package com.nhnacademy.exam.repository;

import com.nhnacademy.exam.entity.member.entity.DepartmentMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentMemberRepository extends JpaRepository<DepartmentMember, Long> {

}
