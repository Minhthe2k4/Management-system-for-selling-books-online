package com.example.demo.config;

import com.example.demo.service.CustomSuccessHandler;
import com.example.demo.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider; // Import this
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder; // This might be needed for a custom AuthenticationManager (less common now)
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter; // No longer needed for filterChain
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.AuthenticationProvider; // Import this

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailService userDetailsService; // Autowire directly, no need for @Bean method separately

    @Autowired
    private CustomSuccessHandler customSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // THIS IS THE CRITICAL ADDITION
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService); // Set your custom UserDetailsService
        authProvider.setPasswordEncoder(passwordEncoder()); // Set your PasswordEncoder
        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/register",
                                "/login",
                                "/css/**",
                                "/js/**",
                                "/cssdangnhap/**",
                                "/jsdangnhap/**",
                                "/assetsdangnhap/**",
                                "/assetsquantri/**",
                                "/fonts/**",
                                "/forms/**",
                                "/images/**",
                                "/META-INF/**",
                                "/WEB-INF/**",
                                "/webapp/**",
                                "/vendor/**").permitAll()

                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/user/**").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/perform_login")
                        .usernameParameter("email") // Should match name="email" in your form
                        .passwordParameter("password") // Should match name="password" in your form
                        .successHandler(customSuccessHandler)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll());

        // You might need to disable CSRF for testing purposes if you haven't added CSRF token to forms
        // but remember to enable and handle it in production.
        // http.csrf(csrf -> csrf.disable()); // Uncomment this if you suspect CSRF issues during login attempts

        return http.build();
    }

    // You no longer need this @Bean method if you're directly autowiring and using
    // the CustomUserDetailService in authenticationProvider()
    // @Bean
    // public UserDetailsService userDetailsService() {
    //     return userDetailsService;
    // }
}