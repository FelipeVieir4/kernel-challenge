package com.br.kchallenge.crud.config; // Ou seu pacote de configuração

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.password.NoOpPasswordEncoder; // Importe este
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Não precisamos de RsaPasswordEncoder aqui POR ENQUANTO

    public SecurityConfig() {
        // Construtor padrão
    }

    // @Bean
    // public PasswordEncoder passwordEncoder() {
    //     // ATENÇÃO: NoOpPasswordEncoder NÃO CRIPTOGRAFA SENHAS.
    //     // Usado APENAS para testes locais e será substituído pelo RSA.
    //     // NÃO USE EM PRODUÇÃO!
    //     System.out.println("AVISO: Usando NoOpPasswordEncoder. As senhas NÃO serão criptografadas!");
    //     return NoOpPasswordEncoder.getInstance();
    // }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/users").permitAll()
                .anyRequest().authenticated()
            )
            .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()));

        return http.build();
    }
}