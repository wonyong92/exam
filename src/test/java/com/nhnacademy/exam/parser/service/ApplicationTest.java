package com.nhnacademy.exam.parser.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
class ApplicationTest {

  @Autowired
  private DataSource dataSource;

  @Test
  void testDataSource() throws SQLException {
    try (Connection connection = dataSource.getConnection()) {
      assertThat(connection).isNotNull();
    }
  }
}
