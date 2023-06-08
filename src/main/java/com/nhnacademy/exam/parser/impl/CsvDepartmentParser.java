package com.nhnacademy.exam.parser.impl;

import com.nhnacademy.exam.parser.DepartmentParser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CsvDepartmentParser implements DepartmentParser {

  @Override
  public String getFileType() {

    return "csv";
  }

  @Override
  public List parsing(File file) throws IOException {
    List<String> lines = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = reader.readLine()) != null) {
        if (line.trim().matches("^[0-9].*[0-9]$")) {
          lines.add(line.trim().replace(" ", ""));
        }
      }
    }

    return lines;
  }
}
