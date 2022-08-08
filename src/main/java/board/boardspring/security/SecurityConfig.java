package board.boardspring.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;


@Configuration @EnableWebSecurity @AllArgsConstructor
public class SecurityConfig  {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/admin").hasRole("USER_ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("login")
                .usernameParameter("email")
                .permitAll()
                .and()
                .logout()
                .permitAll();

        return http.build();

    }
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//
//
//    }
}
