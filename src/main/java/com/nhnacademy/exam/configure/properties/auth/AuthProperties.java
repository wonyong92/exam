package com.nhnacademy.exam.configure.properties.auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "auth")
@Getter
@Setter
public class AuthProperties {

  String userName;

}
