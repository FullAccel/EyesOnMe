package com.hackerton.security.config;

import com.hackerton.member.domain.Role;
import com.hackerton.security.service.CustomOAuth2UserService;
import com.hackerton.security.service.LoginSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final LoginSuccessHandler loginSuccessHandler;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                    .csrf().disable()
                    .headers().frameOptions().disable()
                .and()
                    .authorizeHttpRequests()
                    .requestMatchers("/","/css/**","/images/**","/js/**","/h2-console/**").permitAll()
                    .requestMatchers("/api/v1/**").hasRole(Role.USER.name())
                    .anyRequest().permitAll()
                .and()
                    .logout()
                    .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                    .defaultSuccessUrl("/loginSuccess")
                    .userInfoEndpoint()
                    .userService(customOAuth2UserService)
                .and()
                    .successHandler(loginSuccessHandler);

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/error");
    }
}
