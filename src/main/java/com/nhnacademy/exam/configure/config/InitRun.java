package com.nhnacademy.exam.configure.config;

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
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

@Configuration
@RequiredArgsConstructor
public class InitRun implements CommandLineRunner {
  private final DepartmentParserResolver resolver;
  private final ResourceLoader resourceLoader;
  private final DepartmentRepository departmentRepository;
  private final DepartmentMemberRepository memberRepository;
  private final DepartmentAndMembersRepository departmentAndMembersRepository;

  @Override
  public void run(String... args) {

    String srcName = "classpath:data";
    int count = 0;

    try {
      for (String fileName : Objects.requireNonNull(resourceLoader.getResource(srcName).getFile().list())) {
        DepartmentParser parser = resolver.getDepartmentParser(fileName);
        List<String> parseResult = parser.parsing(resourceLoader.getResource(srcName + "/" + fileName).getFile());
        for (String data : parseResult) {
          String[] splited = data.split(",");
          if (splited.length < 4) {
            continue;
          }
          String memberId = splited[0];
          String memberName = splited[1];
          String departMentName = splited[2];
          String departMentId = splited[3];
          Department department = null;
          if (!departmentRepository.existsById(departMentId)) {
            department = departmentRepository.save(new Department(departMentId, departMentName));
          } else {
            department = departmentRepository.findById(departMentId).orElse(null);
          }

          DepartmentMember member = memberRepository.save(new DepartmentMember(Long.valueOf(memberId), memberName));

          departmentAndMembersRepository.save(new DepartmentAndMembers(new DepartmentAndMembersId(departMentId, Long.valueOf(memberId)), department, member));

          count++;
        }
      }

    } catch (IOException e) {
      log.info("파일 읽기 오류: " + e.getMessage());
    }
    log.info("읽기 결과 : " + count);

  }


}
