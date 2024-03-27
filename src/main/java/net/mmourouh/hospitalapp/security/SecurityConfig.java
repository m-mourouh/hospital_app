package net.mmourouh.hospitalapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(PasswordEncoder passwordEncoder){
        String encodedPassword = passwordEncoder.encode("1234");
        System.out.println(encodedPassword);
        return new InMemoryUserDetailsManager(
                User.withUsername("user1").password(encodedPassword).roles("USER").build(),
                User.withUsername("user2").password(encodedPassword).roles("USER").build(),
                User.withUsername("admin").password(encodedPassword).roles("USER","ADMIN").build()
        );
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.
                formLogin(form -> form.loginPage("/login").permitAll())
                .rememberMe(rememberMe -> rememberMe.key("uniqueAndSecret"))
                .authorizeHttpRequests(auth -> auth.requestMatchers("/webjars/**").permitAll())
//                .authorizeHttpRequests(auth->auth.requestMatchers("/deletePatient/**").hasRole("ADMIN"))
//                .authorizeHttpRequests(auth->auth.requestMatchers("/admin/**").hasRole("ADMIN"))
//                .authorizeHttpRequests(auth->auth.requestMatchers("/user/**").hasRole("USER"))
                .authorizeHttpRequests(auth->auth.anyRequest().authenticated())
                .exceptionHandling(e-> e.accessDeniedPage("/notAuthorized"))
                .build();
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}