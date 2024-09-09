package com.JobPortalWeb.jobwebapp.Config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;


import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.JobPortalWeb.jobwebapp.Security.JwtAuthenticationEntryPoint;
import com.JobPortalWeb.jobwebapp.Security.JwtAuthenticationFilter;
import com.JobPortalWeb.jobwebapp.ServiceImpl.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig

{
	  @Autowired
	    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	    @Autowired
	    private JwtAuthenticationFilter jwtAuthenticationFilter;

	   


	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		
//		UserDetails user= User.builder().username("anand").password(passwordEncoder().encode("anand@123")).roles("ADMIN").build();
//		
//		return new InMemoryUserDetailsManager(user);
//	}

	  @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	  
	  @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception 
	  {
	        return builder.getAuthenticationManager();
	    }
	  
	  @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http.csrf(csrf -> csrf.disable())
	            .cors(cors -> cors.configurationSource(request -> 
	            {
	                CorsConfiguration config = new CorsConfiguration();
	                config.setAllowedOrigins(Arrays.asList("*"));
	                config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	                config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "Accept"));
	                return config;
	            }))
	            .authorizeHttpRequests(auth -> auth
	                .requestMatchers("/auth/login","/employer/**","/jobcategory/**","/employee/**","/jobapplication/**","/bookmark/**","/job/**").permitAll()
//	                .requestMatchers(HttpMethod.GET,"/employee/all").hasRole("Admin")
//	                .requestMatchers(HttpMethod.GET,"/job/alljobs").authenticated()
	                .requestMatchers(HttpMethod.GET,"/{employeeId}/jobs/yourApplications").authenticated()
//	                .requestMatchers("/employer/**").authenticated()
	                .anyRequest().authenticated()
	            )
	            .exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthenticationEntryPoint))
	            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

	        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

	        return http.build();
	    }
}
