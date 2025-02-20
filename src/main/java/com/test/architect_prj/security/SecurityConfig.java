package com.test.architect_prj;

import com.test.architect_prj.security.CustomAccessDeniedHandler;
import com.test.architect_prj.security.CustomLoginFailureHandler;
import com.test.architect_prj.security.CustomLoginSuccessHandler;
import com.test.architect_prj.security.CustomUserDetailsService;
import jakarta.servlet.DispatcherType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity(debug = false)
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private DataSource dataSource; // application.properties에 설정한 spring.datasource D.I

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable()).httpBasic(hbasic -> hbasic.disable())
                .headers(config -> config.frameOptions(customizer -> customizer.sameOrigin()))
                .authorizeHttpRequests(
                        authz -> authz.dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ASYNC).permitAll() // forward																														// 허가
                                .requestMatchers("/", "/login", "/error").permitAll()
                                .requestMatchers("/ceo/**").hasRole("CEO")
                                .requestMatchers("/manager/**").hasAnyRole("CEO", "MANAGER")
                                .anyRequest().authenticated())
                .formLogin(form -> form.loginPage("/login")
                        .successHandler(customLoginSuccessHandler())
                        .failureHandler(customLoginFailureHandler()))
                .sessionManagement(session -> session.maximumSessions(1))
                .exceptionHandling(ex -> ex.accessDeniedHandler(customAccessDeniedHandler()))
                .logout(Customizer.withDefaults()).rememberMe(config -> config.key("e7eKey").tokenValiditySeconds(86400)
                        .tokenRepository(persistentTokenRepository()))
                .build();
    }

    @Bean // remember-me db 연결
    protected PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }

    @Bean // 사용자 정의 UserDetailsService
    protected UserDetailsService customUserDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean // 인증실패 핸들러, 안 맹글어도 크게 안 불편
    protected AuthenticationFailureHandler customLoginFailureHandler() {
        return new CustomLoginFailureHandler();
    }

    @Bean // 인증성공 핸들러, 성공시 URL분배하려면 필요
    protected AuthenticationSuccessHandler customLoginSuccessHandler() {
        return new CustomLoginSuccessHandler();
    }

    @Bean // 접근거부 처리 핸들러, 필요
    protected AccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean // 암호 인코더 기본필요
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean // 인증매니저 스프링 문서 참조, global 설정 복사해옴
    protected AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean // 인증제공자 인증처리
    protected AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;

    }

    @Bean // 동시 로그인수 제어을 위해 필요
    protected HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Bean // webSecurity는 전체적인 설정(구성)에,httpSecurity는 구체적인 세부URL에
    protected WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.debug(false).ignoring().requestMatchers("/css/**", "/js/**", "/img/**", "/favicon.ico");
    }

}
