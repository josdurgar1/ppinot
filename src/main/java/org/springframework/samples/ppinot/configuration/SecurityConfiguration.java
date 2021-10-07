package org.springframework.samples.ppinot.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.samples.ppinot.service.UserService;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author japarejo
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.authorizeRequests()
	    		.antMatchers(HttpMethod.GET, "/","/oups").permitAll()
	            .antMatchers("/").permitAll()
	            .antMatchers("/login").permitAll()
	            .antMatchers("/signup").permitAll()
//	            .anyRequest().denyAll()
	            .antMatchers("/news").permitAll()
	            .antMatchers("/dashboard").hasAnyAuthority("ADMIN")
	            .antMatchers("/dashboard/**").hasAuthority("ADMIN").anyRequest()        
	            .authenticated().and().csrf().disable().formLogin().successHandler(customizeAuthenticationSuccessHandler)
	            .loginPage("/login").failureUrl("/login?error=true")
	            .usernameParameter("username")
	            .passwordParameter("password")
	            .and().logout()
	            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	            .logoutSuccessUrl("/").and().exceptionHandling();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web
	        .ignoring()
	        .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}

	@Bean
	public UserDetailsService mongoUserDetails() {
	    return new UserService();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		 UserDetailsService userDetailsService = mongoUserDetails();
		    auth
		        .userDetailsService(userDetailsService)
		        .passwordEncoder(bCryptPasswordEncoder);
	}
	
//	@Bean
//	public PasswordEncoder passwordEncoder() {	    
//		PasswordEncoder encoder =  NoOpPasswordEncoder.getInstance();
//	    return encoder;
//	}
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	    return bCryptPasswordEncoder;
	
}
}


