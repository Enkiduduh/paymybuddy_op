package com.project.PayMyBuddy.security;

import com.project.PayMyBuddy.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, DaoAuthenticationProvider authProvider) throws Exception {
        http
                .authenticationProvider(authProvider)

                // 1. Politique de création de session
                .sessionManagement(session -> session
                        // Spring ne créera une session que si nécessaire
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                        // Vous pouvez limiter à 1 session par utilisateur
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(true)
                )

                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/connexion","/inscription","/css/**").permitAll() // accessible sans connexion
                        .anyRequest().authenticated() // tout le reste nécessite d'être connecté
                )
                .formLogin(form -> form
                        .loginPage("/connexion")                    // GET pour afficher le formulaire
                        .loginProcessingUrl("/connexion")  // POST pour traiter la soumission
                        .defaultSuccessUrl("/transfert", true)
                        .failureUrl("/connexion?error")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/deconnexion")
                        .logoutSuccessUrl("/connexion?logout")
                        .permitAll()
                );
        return http.build();
    }

}
