package com.example.demo;


import org.springframework.context.annotation.Bean; 
import org.springframework.context.annotation.Configuration; 
import org.springframework.security.config.annotation.web.builders.HttpSecurity; 
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter; 
import org.springframework.security.core.userdetails.User; 
import org.springframework.security.core.userdetails.UserDetails; 
import org.springframework.security.core.userdetails.UserDetailsService; 
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; 
import org.springframework.security.crypto.password.PasswordEncoder; 
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain; 

//import com.spring.security.formlogin.AuthFilter;
 
@SuppressWarnings("deprecation")
@Configuration 
public class WebSecurityConfig extends WebSecurityConfigurerAdapter { 
   
   @Bean 
   protected UserDetailsService userDetailsService() {
   UserDetailsManager userDetailsManager = new InMemoryUserDetailsManager(); 
   UserDetails user = User.withUsername("admin") 
   .password(passwordEncoder().encode("12345")) 
      .authorities("read") .build(); 
      userDetailsManager.createUser(user); 
      return userDetailsManager; 
      
   }
   @Bean 
   protected PasswordEncoder passwordEncoder() { 
      return new BCryptPasswordEncoder(); }; 
      
      protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	  http.authorizeRequests().antMatchers("/").permitAll();
    	  return http.build();
      }
      
      @Override 
      protected void configure(HttpSecurity http) throws Exception { 
    	  http.authorizeRequests().antMatchers("/").permitAll();
    	  
      http.csrf().disable() .authorizeRequests().anyRequest()
      .authenticated() .and() 
      .formLogin() 
      .and() 
      .rememberMe() 
      .and() .logout() .logoutUrl("/logout") 
      .logoutSuccessUrl("/login") .deleteCookies("remember-me"); 
   } 
}