package com.nhnacademy.exam.controller.department;

import com.nhnacademy.exam.entity.department.dto.SimpleDepartmentDto;
import com.nhnacademy.exam.service.department.DepartmentService;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

  private final DepartmentService departmentService;

  public DepartmentController(DepartmentService departmentService) {
    this.departmentService = departmentService;
  }

  @PostMapping
  public ResponseEntity<Map<String, Object>> addDepartment(@RequestBody SimpleDepartmentDto departmentDto) {
    String id = departmentService.addDepartment(departmentDto);
    return new ResponseEntity<>(Map.of("id", id), HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public SimpleDepartmentDto getDepartment(@PathVariable String id) {
    return departmentService.getDepartmentDto(id);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> updateDepartment(@PathVariable String id, @RequestBody SimpleDepartmentDto departmentDto) {
    departmentService.updateDepartment(id, departmentDto);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteDepartment(@PathVariable String id) {
    if (departmentService.deleteDepartment(id)) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  }
}