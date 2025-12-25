package com.sumeet.erp.common.security;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.sumeet.erp.common.AppProperties;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig {

  private final AppProperties appProperties;

  @Autowired
  public SecurityConfig(AppProperties appProperties) {
    this.appProperties = Objects.requireNonNull(appProperties, "appProperties must not be null");
  }

  @Bean
  SecurityFilterChain security(HttpSecurity http) throws Exception {
    Objects.requireNonNull(http, "http must not be null");
    http
      .csrf(csrf -> csrf.disable())
      .authorizeHttpRequests(auth -> auth
        .requestMatchers(appProperties.getSecurity().getPermittedPaths().toArray(new String[0])).permitAll()
        .anyRequest().authenticated())
      .oauth2ResourceServer(oauth -> oauth.jwt(Customizer.withDefaults()));
    return http.build();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}