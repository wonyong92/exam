package com.nhnacademy.exam.service.department;

import com.nhnacademy.exam.entity.department.dto.SimpleDepartmentDto;
import com.nhnacademy.exam.entity.department.entity.Department;
import com.nhnacademy.exam.exception.DepartmentExistsException;
import com.nhnacademy.exam.exception.DepartmentNotExistsException;
import com.nhnacademy.exam.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Throwable.class})
public class DepartmentService {

  private final DepartmentRepository repository;

  public String addDepartment(SimpleDepartmentDto departmentDto) {
    Department find = repository.findById(departmentDto.getId()).orElse(null);
    if (find != null) {
      throw new DepartmentExistsException(find.getId());
    }
    Department department = new Department(departmentDto.getId(), departmentDto.getName());

    Department savedDepartment = repository.save(department);
    return savedDepartment.getId();
  }

  public SimpleDepartmentDto getDepartmentDto(String id) {
    Department department = repository.findById(id).orElse(null);

    if (department == null) {
      throw new DepartmentNotExistsException(id);
    }

    return new SimpleDepartmentDto(department.getId(), department.getName());
  }

  public void updateDepartment(String id, SimpleDepartmentDto departmentDto) {
    Department department = repository.findById(id).orElse(null);

    if (department == null) {
      throw new DepartmentNotExistsException(id);
    }
    repository.save(new Department(id, departmentDto.getName(), department.getDepartmentAndMembers()));
  }

  public boolean deleteDepartment(String id) {
    Department find = repository.findById(id).orElse(null);
    if (find == null) {
      throw new DepartmentNotExistsException(id);
    }
    repository.deleteById(find.getId());
    return true;
  }
}
