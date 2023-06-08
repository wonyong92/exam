package com.nhnacademy.exam.parser;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DepartmentParserResolver {

  private final List<DepartmentParser> departmentParserList;

  public DepartmentParser getDepartmentParser(String fileName) {
    DepartmentParser find = null;
    for (DepartmentParser parser : departmentParserList) {
      if (parser.matchFileType(fileName)) {
        find = parser;
        break;
      }
    }

    return find;

  }

}
