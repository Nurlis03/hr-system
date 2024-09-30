package org.example.hrsystem.config;

import lombok.AllArgsConstructor;
import org.example.hrsystem.enums.Role;
import org.example.hrsystem.security.JwtTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
public class WebSecurityConfig {

    private final JwtTokenFilter jwtTokenFilter;

//    private final CustomOAuth2UserService customOAuth2UserService;
//    private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
//                .oauth2Login(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req
                        .antMatchers("/api/v1/job-applications/applicant/**").hasAuthority(Role.APPLICANT.name())
                        .antMatchers("/api/v1/job-applications/hr/**").hasAuthority(Role.HR_SPECIALIST.name())
                        .antMatchers("/api/v1/vacancies/**").hasAuthority(Role.HR_SPECIALIST.name())

                        .antMatchers("/api/v1/work-schedules/**").hasAnyAuthority(Role.ADMINISTRATOR.name(), Role.HR_SPECIALIST.name())
                        .antMatchers("/api/v1/industries/**").hasAnyAuthority(Role.ADMINISTRATOR.name(), Role.HR_SPECIALIST.name())
                        .antMatchers("/api/v1/job-types/**").hasAnyAuthority(Role.ADMINISTRATOR.name(), Role.HR_SPECIALIST.name())

                        .antMatchers("/api/v1/admin/**").hasAuthority(Role.ADMINISTRATOR.name())
                        .antMatchers("/api/v1/auth/**", "/error").permitAll()
                        .antMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
