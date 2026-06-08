package br.com.ramos.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        // Desabilita formulário de login (Não queremos HTML)
        httpSecurity.formLogin(form -> form.disable());

        // Desabilita CSRF temporariamente
        httpSecurity.csrf(csrf -> csrf.disable());

        // Permite todas as requisições por enquanto
        httpSecurity.authorizeHttpRequests(auth ->
                auth.anyRequest().permitAll()
        );

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
