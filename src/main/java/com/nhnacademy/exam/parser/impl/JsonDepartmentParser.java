package com.nhnacademy.exam.parser.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.exam.parser.DepartmentParser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class JsonDepartmentParser implements DepartmentParser {

  private final ObjectMapper objectMapper;

  @Override
  public String getFileType() {
    return "json";
  }

  public List parsing(File file) throws IOException {
    StringBuilder sb = new StringBuilder();

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = reader.readLine()) != null) {
        sb.append(line);
        sb.append(System.lineSeparator()); // 각 라인 사이에 줄바꿈 문자를 추가합니다.
      }
    }

    String readResult = sb.toString();
    JsonNode[] jsonNodes = objectMapper.readValue(readResult.trim(), JsonNode[].class);

    return Arrays.stream(jsonNodes).map(
        node -> {
          String map = "";
          map += node.get("사번").asText();
          map += ",";
          map += node.get("이름").asText();
          map += ",";
          map += node.get("부서").asText();
          map += ",";
          map += node.get("부서코드").asText();
          return map;
        }
    ).collect(Collectors.toList());
  }
}

