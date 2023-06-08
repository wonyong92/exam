package com.nhnacademy.exam.repository;

import com.nhnacademy.exam.entity.department.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, String> {

}
