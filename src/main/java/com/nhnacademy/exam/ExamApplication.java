package com.nhnacademy.exam;

import com.nhnacademy.exam.entity.DepartmentAndMembers;
import com.nhnacademy.exam.entity.DepartmentAndMembersId;
import com.nhnacademy.exam.entity.department.entity.Department;
import com.nhnacademy.exam.entity.member.entity.DepartmentMember;
import com.nhnacademy.exam.parser.DepartmentParser;
import com.nhnacademy.exam.parser.DepartmentParserResolver;
import com.nhnacademy.exam.repository.DepartmentAndMembersRepository;
import com.nhnacademy.exam.repository.DepartmentMemberRepository;
import com.nhnacademy.exam.repository.DepartmentRepository;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.core.io.ResourceLoader;

@Slf4j
@SpringBootApplication
@ConfigurationPropertiesScan
@RequiredArgsConstructor
public class ExamApplication {

  private final DepartmentParserResolver resolver;
  private final ResourceLoader resourceLoader;
  private final DepartmentRepository departmentRepository;
  private final DepartmentMemberRepository memberRepository;
  private final DepartmentAndMembersRepository departmentAndMembersRepository;


  public static void main(String[] args) {
    SpringApplication.run(ExamApplication.class, args);
  }


}
