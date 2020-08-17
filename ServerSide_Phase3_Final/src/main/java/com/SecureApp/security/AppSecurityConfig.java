package com.SecureApp.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity

public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
	
//	@Autowired
//    private JwtAuthEntryPoint unauthorizedHandler;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // This Origin header you can see that in Network tab
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200/", "http:/url_2")); 
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "DELETE", "PUT"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control","Content-Type", "content-type", "x-requested-with", "Access-Control-Allow-Origin",
        		"Access-Control-Allow-Headers", "x-auth-token", "x-app-id", "Origin","Accept", "X-Requested-With",
        		"Access-Control-Request-Method", "Access-Control-Request-Headers"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
   
   
//	@Autowired
//	private DataSource securityDataSource;
//	
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		
//		auth.jdbcAuthentication().dataSource(securityDataSource);
//	}
    

	
	
	@Bean
	public AuthenticationProvider authProvider() {
		
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		return provider;
	}
	
	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http
//		.requestMatchers().antMatchers("/auth/**", "/logout", "/oauth/authorize", "/oauth/confirm_access")
//		  .and()
		   // .cors().configurationSource(configurationSource()).and()
		    .csrf().disable().authorizeRequests().antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
		.antMatchers("/general/**").permitAll()
		.antMatchers("/auth/**").permitAll()
		.antMatchers("/customers/**").hasRole("CUSTOMER")
		.antMatchers("/companies/**").hasRole("COMPANY")
		.antMatchers("/admin/**").hasRole("ADMIN")
		.anyRequest().authenticated().and()
		//.formLogin()
		//.permitAll().and()
		.logout().logoutUrl("/logout").permitAll()
		.and().exceptionHandling()
		//.authenticationEntryPoint(unauthorizedHandler).and()
		//.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.httpBasic();
			
	}
	
}
