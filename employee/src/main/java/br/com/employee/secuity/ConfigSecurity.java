package br.com.employee.secuity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class ConfigSecurity {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.POST, "/api/employee/create").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/employee/findByDocument/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/employee/findAll").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/api/employee/delete/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/api/employee/put/**").permitAll()
                .anyRequest().authenticated()
            )
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
	
}
