package com.airtribe.employeetrackingsystem.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        //.requestMatchers("/test/**").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers("/departments/**", "/projects/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
                        .requestMatchers("/employees/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_EMPLOYEE")
                        .anyRequest().authenticated()
                )
                .oauth2Login(httpSecurityOAuth2LoginConfigurer -> httpSecurityOAuth2LoginConfigurer
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login?error"))
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())));
        return http.build();
    }

    private JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

        grantedAuthoritiesConverter.setAuthoritiesClaimName("groups");  // Assign roles from groups
        grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_"); // Prefix roles with "ROLE_"

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }

    @Bean
    public NimbusJwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri("https://dev-83832482.okta.com/oauth2/default/v1/keys").build();
    }
}
