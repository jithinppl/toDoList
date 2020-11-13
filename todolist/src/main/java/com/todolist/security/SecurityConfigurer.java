package com.todolist.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {
	@Autowired
	private MyUserDetailsService myuserDetailsService;
	//@Autowired
//	private JwtRequestFilter jwtRequestFilter;
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Override
	public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/authentication");
        web.ignoring().antMatchers("/h2-console");
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.cors().disable().csrf().disable()
		// dont authenticate this particular request
	.authorizeRequests().anyRequest().permitAll();//.antMatchers("/authentication").permitAll().
		http.headers().frameOptions().disable();
		//antMatchers("/api/user/signup").permitAll().
		//antMatchers("/h2-console").permitAll();
		// all other requests need to be authenticated
		//anyRequest().authenticated();
		// make sure we use stateless session; session won't be used to
		// store user's state.
		//exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
	//	.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	//	http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}

	public void Configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myuserDetailsService);

	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
