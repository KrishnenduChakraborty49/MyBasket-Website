package com.jpa.concepts.jpa_concepts.config;

import ch.qos.logback.core.joran.spi.HttpUtil;
import com.jpa.concepts.jpa_concepts.security.JwtAuthenticationEntryPoint;
import com.jpa.concepts.jpa_concepts.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final AccessDeniedHandler accessDeniedHandler;
//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        UserDetails user1 = User.builder().username("aditya").password("{noop}abc").build();
//        UserDetails user2 = User.builder().username("aman").password("{noop}abc").build();
//
//        return new InMemoryUserDetailsManager(user1,user2);
//
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())   // 🔥 THIS IS THE KEY FIX
                .authorizeHttpRequests(
                        requests ->
//                                requests.requestMatchers(HttpMethod.GET).permitAll()
//                                        .requestMatchers(HttpMethod.POST).hasRole("NORMAL")
//                                        .requestMatchers(HttpMethod.PUT).hasRole("ADMIN")
//                                        .requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")
                                requests.anyRequest().permitAll()

                )
                .exceptionHandling(ex->ex.authenticationEntryPoint(jwtAuthenticationEntryPoint).accessDeniedHandler(accessDeniedHandler))
                .sessionManagement(e -> e.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);




        return http.build();
    }




//    @Bean
//    public PasswordEncoder encoder(){
//        reutrn
//    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // ✅ plain text
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) {
        return configuration.getAuthenticationManager();
    }
}
