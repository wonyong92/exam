package com.nhnacademy.exam.controller.members;

import com.nhnacademy.exam.entity.member.dto.DepartMemberDto;
import com.nhnacademy.exam.entity.member.dto.DepartMembersDto;
import com.nhnacademy.exam.service.member.DepartmentMemberService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department-members")
public class DepartmentMembersController {

  private final DepartmentMemberService departmentMemberService;

  public DepartmentMembersController(DepartmentMemberService departmentMemberService) {
    this.departmentMemberService = departmentMemberService;
  }

  @GetMapping
  public ResponseEntity<List<DepartMemberDto>> getDepartmentMembers(@RequestParam String departmentIds) throws MissingServletRequestParameterException {
    if (departmentIds.trim().equals("")) {
      throw new MissingServletRequestParameterException("departmentIds", "String");
    }

    DepartMembersDto departmentMembers = departmentMemberService.getDepartmentMembers(departmentIds);

    List<DepartMemberDto> result = departmentMembers.getList();
    result.sort((d1, d2) -> (int) (d1.getEmployee().getId() - d2.getEmployee().getId()));
    return ResponseEntity.ok(result);
  }


}