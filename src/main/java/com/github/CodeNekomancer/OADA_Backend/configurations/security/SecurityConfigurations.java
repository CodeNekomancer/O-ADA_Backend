package com.github.CodeNekomancer.OADA_Backend.configurations.security;

import com.github.CodeNekomancer.OADA_Backend.configurations.security.jwt.JwtAuthorizationFilter;
import io.swagger.models.HttpMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

    private final AuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().authorizeRequests()

                /*
                .antMatchers(String.valueOf(HttpMethod.GET), "/adacc/add").permitAll()
                .antMatchers(String.valueOf(HttpMethod.GET), "/adacc/del").permitAll()
                .antMatchers(String.valueOf(HttpMethod.GET), "/adacc/get/all").permitAll()
                .antMatchers(String.valueOf(HttpMethod.GET), "/adacc/get/pag").permitAll()
                .antMatchers(String.valueOf(HttpMethod.GET), "/adacc/get/sng").permitAll()
                .antMatchers(String.valueOf(HttpMethod.GET), "/adacc/edit").hasRole("ADMIN")

                .antMatchers(HttpMethod.POST, "/alumno/**").permitAll()
                .antMatchers(HttpMethod.POST, "/profesor/**").permitAll()
                .antMatchers(HttpMethod.POST, "/responsable/**").permitAll()
                .antMatchers(HttpMethod.POST, "/user/**").hasRole("ADMIN")

                .antMatchers(HttpMethod.GET, "/alumno/**").permitAll()
                .antMatchers(HttpMethod.GET, "/profesor/**").permitAll()
                .antMatchers(HttpMethod.GET, "/responsable/**").permitAll()
                .antMatchers(HttpMethod.GET, "/user/**").permitAll()

                .antMatchers(HttpMethod.PUT, "/alumno/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/profesor/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/responsable/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/user/**").hasRole("ADMIN")

                .antMatchers(HttpMethod.DELETE, "/alumno/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/profesor/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/responsable/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/user/**").hasRole("ADMIN")
                */


                .anyRequest().permitAll().and().csrf().disable();

        http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}