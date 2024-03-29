package com.cp.springboot.appmanagement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	public void ConfigureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance()).withUser("admin").password("admin").roles("USER","ADMIN");
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests().antMatchers("/login","/h2-console/**").permitAll().antMatchers("/","/*appointment*/**").access("hasRole('USER')").and().formLogin();
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}
}
