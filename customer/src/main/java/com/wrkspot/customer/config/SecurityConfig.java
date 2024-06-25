package com.wrkspot.customer.config;

import com.wrkspot.customer.filter.JwtAuthFilter;
import com.wrkspot.customer.service.UserInfoUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * This class configures the security settings for the application.
 * It sets up JWT authentication, user details service, password encoding, and the security filter chain.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * The JWT authentication filter.
     * This filter will intercept incoming requests to validate the JWT token.
     */
    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    /**
     * Configures a UserDetailsService bean.
     * This service is responsible for loading user-specific data, such as user details.
     *
     * @return a UserInfoUserDetailsService instance, which is a custom implementation of UserDetailsService.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserInfoUserDetailsService();
    }

    /**
     * Configures the security filter chain.
     * This method sets up HTTP security configurations including CSRF protection, request authorization,
     * session management, authentication provider, and the JWT authentication filter.
     *
     * @param http the HttpSecurity instance to configure.
     * @return the configured SecurityFilterChain instance.
     * @throws Exception if an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // Disables CSRF protection as we are using JWT tokens which are immune to CSRF attacks.
                .csrf(AbstractHttpConfigurer::disable)
                // Configures authorization for different request patterns.
                .authorizeHttpRequests(authz -> authz
                        // Allows only users with 'ADMIN' role to access '/api/customers/create'
                        .requestMatchers("/api/customers/create").hasRole("ADMIN")
                        // Requires authentication for '/api/customers'
                        .requestMatchers("/api/customers").authenticated()
                        // Requires authentication for any request that matches the pattern '/api/customers?**'
                        .requestMatchers("/api/customers?**").authenticated()
                        .requestMatchers("/api/customers/only-in-a").authenticated()
                        .requestMatchers("/api/customers/only-in-b").authenticated()
                        .requestMatchers("/api/customers/in-both-a-and-b").authenticated()
                        // Allows all other requests without authentication
                        .anyRequest().permitAll()
                )
                // Configures session management to be stateless as JWT is used for authentication.
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Configures the custom authentication provider.
                .authenticationProvider(authenticationProvider())
                // Adds the JWT authentication filter before the UsernamePasswordAuthenticationFilter in the filter chain.
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                // Builds the SecurityFilterChain object.
                .build();
    }

    /**
     * Configures a PasswordEncoder bean.
     * This encoder is used to securely hash and verify passwords.
     *
     * @return a BCryptPasswordEncoder instance, which implements a strong hashing algorithm.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configures an AuthenticationProvider bean.
     * This provider uses a DaoAuthenticationProvider to perform authentication by loading user details
     * and checking passwords.
     *
     * @return the configured DaoAuthenticationProvider instance.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        // Creates a new instance of DaoAuthenticationProvider.
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        // Sets the UserDetailsService that the DaoAuthenticationProvider will use to load user details.
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());

        // Sets the PasswordEncoder that the DaoAuthenticationProvider will use to encode and verify passwords.
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        // Returns the configured DaoAuthenticationProvider instance.
        return daoAuthenticationProvider;
    }

    /**
     * Configures an AuthenticationManager bean.
     * This manager is used to handle authentication requests.
     *
     * @param configuration the AuthenticationConfiguration instance to configure.
     * @return the configured AuthenticationManager instance.
     * @throws Exception if an error occurs during configuration.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
