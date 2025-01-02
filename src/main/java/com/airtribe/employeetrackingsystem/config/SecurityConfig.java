//package com.airtribe.employeetrackingsystem.config;
//
//import com.airtribe.employeetrackingsystem.enums.RolesEnum;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests((authz) -> authz
//                        //.requestMatchers(HttpMethod.POST,"/employees/employee").permitAll()
//                        .requestMatchers("/employees/{id}").hasAnyRole(RolesEnum.ADMIN.name(), RolesEnum.MANAGER.name(), RolesEnum.EMPLOYEE.name())
//                        .requestMatchers("/employees/**").hasAnyRole(RolesEnum.ADMIN.name(), RolesEnum.MANAGER.name())
//                        .anyRequest().authenticated()
//                )
//                .httpBasic(withDefaults());
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//}
