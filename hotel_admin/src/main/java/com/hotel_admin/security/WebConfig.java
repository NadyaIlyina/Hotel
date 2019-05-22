package com.hotel_admin.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity
public class WebConfig extends WebSecurityConfigurerAdapter {
    @Value("${cors.origins}")
    private String[] origins;
    @Value("${cors.methods}")
    private String[] methods;
    @Value("${cors.headers}")
    private String[] headers;
    private UserDetailsService userDetailsService;

    public WebConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
//                .antMatchers("/admin*","/api/**").hasAuthority("ADMIN")
                .antMatchers("/reservation", "/profile").authenticated()
                .and().formLogin()
                .loginPage("/login").usernameParameter("login").permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordEnconderTest());
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setExposedHeaders(Arrays.asList(headers));
        corsConfiguration.setAllowedHeaders(Arrays.asList(headers));
        corsConfiguration.setAllowedMethods(Arrays.asList(methods));
        corsConfiguration.setAllowedOrigins(Arrays.asList(origins));
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

}
