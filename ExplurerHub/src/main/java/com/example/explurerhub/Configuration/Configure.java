package com.example.explurerhub.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class Configure {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        // كويري مخصص لجلب بيانات المستخدم
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "SELECT username, password, enabled FROM users WHERE username = ?"
        );

        // كويري مخصص لجلب الصلاحيات
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "SELECT u.username, r.name AS authority " +
                        "FROM users u " +
                        "JOIN users_roles ur ON u.id = ur.user_id " +
                        "JOIN roles r ON ur.role_id = r.id " +
                        "WHERE u.username = ?"
        );

        return jdbcUserDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // تعطيل CSRF فقط لمسارات الـ API الخاصة بالـ AJAX
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/show/saveUser")
                        .ignoringRequestMatchers("/rate")
                        .ignoringRequestMatchers("/chat/ask")   // 👈 مهم جداً
                        .ignoringRequestMatchers("/chat/plan")  // 👈 مهم جداً
                )

                .authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers("/signup", "/saveUser", "/css/**", "/js/**").permitAll()
                                .requestMatchers("/cart/**", "/add-to-cart/**").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/manageUsers").hasAnyRole("ADMIN","USER")
                                .requestMatchers("/rate").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/show/pages").hasAnyRole("USER", "ADMIN")

                                // السماح للصفحة ومسارات الـ AJAX
                                .requestMatchers("/chat", "/chat/**").permitAll()

                                .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/show/pages", true)
                        .permitAll()
                )
                .logout(logout -> logout.permitAll());

        return http.build();
    }


}
