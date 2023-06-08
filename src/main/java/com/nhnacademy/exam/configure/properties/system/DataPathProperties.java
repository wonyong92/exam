package com.nhnacademy.exam.configure.properties.system;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "system.record")
@Getter
@Setter
public class DataPathProperties {

  String path;

}
