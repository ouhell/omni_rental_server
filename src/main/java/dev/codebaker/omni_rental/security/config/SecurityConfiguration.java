package dev.codebaker.omni_rental.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Stack;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {




    @Bean
    public  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .formLogin(formLogin -> formLogin.disable())
        .authorizeHttpRequests(auth ->
            auth.requestMatchers("/api/v1/general/version").permitAll()
            .requestMatchers(
                            "/swagger-ui/**",
                            "/v3/api-docs/**",
                                "/swagger-docs"
                    ).permitAll()
            .anyRequest().authenticated()

        );


        return http.build();
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
