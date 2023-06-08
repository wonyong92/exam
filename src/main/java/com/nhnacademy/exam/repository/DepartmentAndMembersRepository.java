package com.nhnacademy.exam.repository;

import com.nhnacademy.exam.entity.DepartmentAndMembers;
import com.nhnacademy.exam.entity.DepartmentAndMembersId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentAndMembersRepository extends JpaRepository<DepartmentAndMembers, DepartmentAndMembersId> {

}
