package com.nhnacademy.exam.interceptor;

import com.nhnacademy.exam.configure.properties.auth.AuthProperties;
import com.nhnacademy.exam.exception.UsernameHeaderNotFound;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
@RequiredArgsConstructor
public class SimpleSecurityInterceptor implements HandlerInterceptor {

  private final AuthProperties properties;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    String userName = properties.getUserName();
    if (userName == null || userName.trim().equals("")) {
      userName = "nhnacademy";
    }
    String userNameHeader = request.getHeader("X-USER-ID");
    if (userNameHeader == null || userNameHeader.trim().equals("")) {
      throw new UsernameHeaderNotFound();
    }

    if (userNameHeader.trim().equals(userName)) {
      return true;
    } else {
      throw new UsernameHeaderNotFound();
    }
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
  }
}
