package com.appRomeAndy.SecurityConfig;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;



@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
            .antMatchers("/login","/app/coment/**","/app/user/**").permitAll() //permitimos el acceso a /login a cualquiera
            .anyRequest().authenticated() //cualquier otra peticion requiere autenticacion
            .and()
            // Las peticiones /login pasaran previamente por este filtro
            .addFilterBefore(new LoginFilter("/login", authenticationManager()),UsernamePasswordAuthenticationFilter.class)

            // Las demás peticiones pasarán por este filtro para validar el token
            .addFilterBefore(new JwtFilter(),UsernamePasswordAuthenticationFilter.class);
            
            
            
      
    }
	
	

		@Bean
	    @Override
		public UserDetailsService userDetailsService() {
			UserDetails user =
				 User.withDefaultPasswordEncoder()
					.username("duvanceliz")
					.password("Duvan1997")
					.roles("USER")
					.build();

			return new InMemoryUserDetailsManager(user);
		}
	   
		
		  @Bean 
		  public CorsConfigurationSource corsConfigurationSource() { 
		  CorsConfiguration configuration = new CorsConfiguration();
		  configuration.setAllowedOrigins(Arrays.asList("*"));
		  configuration.setAllowedMethods(Arrays.asList("*"));
		  configuration.addAllowedHeader("*");
		  UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(); 
		  source.registerCorsConfiguration("/**",configuration); 
		  return source; }    

}
