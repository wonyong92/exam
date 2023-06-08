package com.nhnacademy.exam.service.member;

import com.nhnacademy.exam.entity.DepartmentAndMembers;
import com.nhnacademy.exam.entity.department.dto.SimpleDepartmentDto;
import com.nhnacademy.exam.entity.department.entity.Department;
import com.nhnacademy.exam.entity.member.dto.DepartMemberDto;
import com.nhnacademy.exam.entity.member.dto.DepartMembersDto;
import com.nhnacademy.exam.entity.member.dto.SimpleMemberDto;
import com.nhnacademy.exam.repository.DepartmentMemberRepository;
import com.nhnacademy.exam.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepartmentMemberService {


  final DepartmentMemberRepository memberRepository;
  final DepartmentRepository departmentRepository;

  public DepartMembersDto getDepartmentMembers(String departmentIds) {
    log.info("here");

    String[] dIds = departmentIds.split(",");
    DepartMembersDto dto = new DepartMembersDto();
    for (String id : dIds) {
      log.info("부서 아이디 : " + id);
      Department find = departmentRepository.findById(id).orElse(null);
      if (find != null) {
        for (DepartmentAndMembers data : find.getDepartmentAndMembers()) {
          dto.add(new DepartMemberDto(new SimpleDepartmentDto(find), new SimpleMemberDto(data.getDepartmentMember())));
        }
      }
    }

    return dto;
  }
}
