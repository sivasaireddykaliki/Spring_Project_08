package com.example.spring_project_08.security;

import com.example.spring_project_08.config.JwtAuthenticationFilter;
import com.example.spring_project_08.entity.Permission;
import com.example.spring_project_08.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class DemoSecurityConfig {



    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        /*

            admin  = get,put
            manager= evrything
            principal = get,post
            employee = get
         */
        http.authorizeHttpRequests(
                auth -> auth
                        
                        .requestMatchers(HttpMethod.POST,"/api/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/excel").permitAll()
                        .requestMatchers(HttpMethod.GET,"/open-api/**").hasAnyRole(Role.ALBUM.name())
                        .requestMatchers(HttpMethod.GET,"/api/manager/**").hasAnyRole(Role.MANAGER.name())
                        .requestMatchers(HttpMethod.PUT,"/api/manager/**").hasAnyRole(Role.MANAGER.name())
                        .requestMatchers(HttpMethod.DELETE,"/api/manager/**").hasAnyRole(Role.MANAGER.name())
                        .requestMatchers(HttpMethod.GET,"/api/book/**").hasAnyRole(Role.MANAGER.name(),Role.PRINCIPAL.name(),Role.ADMIN.name(),Role.EMPLOYEE.name())
                        .requestMatchers(HttpMethod.GET,"/api/books").hasAnyRole(Role.MANAGER.name(),Role.PRINCIPAL.name(),Role.ADMIN.name(),Role.EMPLOYEE.name())
                        .requestMatchers(HttpMethod.POST,"/api/books").hasAnyRole(Role.MANAGER.name(),Role.PRINCIPAL.name())
                        // define role access for API without http method parameter then authority or permission access for API with http method constants
                        .requestMatchers("/api/books/**").hasAnyRole(Role.MANAGER.name(),Role.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT,"/api/books/**").hasAnyAuthority(Permission.ADMIN_UPDATE.name(),Permission.MANAGER_UPDATE.name())
                        .requestMatchers(HttpMethod.DELETE,"/api/books/**").hasAnyAuthority(Permission.ADMIN_DELETE.name())
                        .requestMatchers(HttpMethod.GET,"/api/v1/management/**").hasAnyAuthority(Permission.MANAGER_READ.name())
                        .anyRequest().authenticated()
        );

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authenticationProvider(authenticationProvider);

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);


        //disable csrf
        http.csrf(csrf-> csrf.disable());

        return http.build();
    }


}
