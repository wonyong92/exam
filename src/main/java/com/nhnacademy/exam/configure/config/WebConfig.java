package com.nhnacademy.exam.configure.config;

import com.nhnacademy.exam.configure.properties.auth.AuthProperties;
import com.nhnacademy.exam.interceptor.QueryParameterCheckerInterceptor;
import com.nhnacademy.exam.interceptor.SimpleHeaderCheckerInterceptor;
import com.nhnacademy.exam.interceptor.SimpleSecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Autowired
  AuthProperties properties;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {

    registry.addInterceptor(new SimpleSecurityInterceptor(properties));
    registry.addInterceptor(new SimpleHeaderCheckerInterceptor());
    registry.addInterceptor(new QueryParameterCheckerInterceptor());
  }
}
