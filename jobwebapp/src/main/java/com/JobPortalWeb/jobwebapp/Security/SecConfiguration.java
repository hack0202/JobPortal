package com.JobPortalWeb.jobwebapp.Security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;


@Configuration
public class SecConfiguration {
//	@Autowired
//    private JwtAuthenticationEntryPoint point;
//    @Autowired
//    private JwtAuthenticationFilter filter;
    
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//
//    	http.csrf(csrf -> csrf.disable())
//        .cors(cors -> cors.configurationSource(request -> {
//   	    CorsConfiguration config = new CorsConfiguration();
//   	    config.setAllowedOrigins(Arrays.asList("*")); // Add your allowed origins here
//   	    config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//   	    config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "Accept"));
//   	    return config;
//   	}))
//               
//        		.authorizeHttpRequests(auth->auth.requestMatchers("*").authenticated()
//        				.requestMatchers("/auth/login").permitAll()
//        				.requestMatchers("/employee/**").authenticated())
//               .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
//               .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//       http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
//       return http.build();
	
//   }
    }

