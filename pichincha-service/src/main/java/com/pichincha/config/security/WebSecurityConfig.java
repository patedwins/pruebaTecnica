package com.pichincha.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.Serializable;

/**
 * WebSecurityConfig.
 *
 * @author patedwins on 2024/04/12.
 * @version 1.0.0
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private transient JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private transient JwtRequestFilter jwtRequestFilter;

    /**
     * passwordEncoder.
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests()
                .antMatchers("/authenticate", "/actuator/**", "/transaccion/**",
                        "/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**", "/cuenta/**",
                        "/entidad/**", "/movimiento/**").permitAll()

                // Se debe agregar aqui el path de aquellos servicios que se desea que sean públicos.
                // Ejemplo:
                //.antMatchers("/rest.service/**").permitAll()

                .anyRequest().authenticated().and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
