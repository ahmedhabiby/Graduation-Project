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
                // بما أننا نستخدم نموذج تسجيل دخول عادي في Thymeleaf، سنقوم بتمكين CSRF
                // إذا لم نكن نريد إضافة التوكن في الفورم، يمكننا الإبقاء على csrf.disable()
                // ولكن لأفضل ممارسات الأمان، سنزيل التعطيل (Spring Security سيتعامل معه)
                .csrf(csrf -> csrf.ignoringRequestMatchers("/show/saveUser")) // تجاهل CSRF لعملية تسجيل المستخدم الجديدة (POST)
                .authorizeHttpRequests(configurer ->
                        configurer
                                // السماح بالوصول لصفحة التسجيل وحفظ المستخدم والموارد الثابتة
                                .requestMatchers("/show/signup", "/show/saveUser", "/css/**", "/js/**").permitAll()
                                // حماية صفحة الصفحات (Pages) لأي مستخدم لديه دور USER أو ADMIN
                                .requestMatchers("/show/pages").hasAnyRole("USER", "ADMIN")
                                // حماية جميع الطلبات الأخرى
                                .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login") // مسار معالجة تسجيل الدخول (يجب أن يطابق الـ action في الـ form)
                        // استخدام defaultSuccessUrl لتبسيط عملية التوجيه بعد نجاح تسجيل الدخول
                        .defaultSuccessUrl("/show/pages", true)
                        .permitAll()
                )
                .logout(logout -> logout.permitAll());
        return http.build();
    }

}
