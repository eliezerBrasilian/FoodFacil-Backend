package com.br.foodfacil.config;

import com.br.foodfacil.filter.SecurityFilter;
import com.br.foodfacil.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST,AppUtils.baseUrl + "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST,AppUtils.baseUrl + "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST,AppUtils.baseUrl + "/salgado").authenticated()
                        .requestMatchers(HttpMethod.POST,AppUtils.baseUrl + "/adicional").authenticated()
                        .requestMatchers(HttpMethod.POST,AppUtils.baseUrl + "/exibir_vendas").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,AppUtils.baseUrl + "/pedido").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,AppUtils.baseUrl + "/salgado").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,AppUtils.baseUrl + "/notificacao").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,AppUtils.baseUrl + "/sabor").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,AppUtils.baseUrl + "/sabor/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,AppUtils.baseUrl + "/sabor/{id}").hasRole("ADMIN")
                        .requestMatchers("/swagger-ui/**","/v3/api-docs/**").permitAll()
                        .anyRequest().permitAll()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}