package com.store.storeApp.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.store.storeApp.filter.AuthorizationFilter;
import com.store.storeApp.service.UserService;

@Configuration
public class SecurityConfig 
{
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthorizationFilter authorizationFilter)
            throws Exception {

        http.cors();
        http.csrf().disable();

        http.addFilterAt(authorizationFilter, BasicAuthenticationFilter.class);

        return http.build();
    }
	
	@Bean
	public WebSecurityCustomizer webSecurityCostumizer()
	{
		return(web) -> matchers().forEach(r->{
			web.ignoring().requestMatchers(r);
		});
	}
	
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http, UserService userService, BCryptPasswordEncoder encoder) throws Exception
	{
		return http.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(userService).passwordEncoder(encoder).and().build();
	}
	
	@Bean
	public List<RequestMatcher> matchers()
	{
		final List<RequestMatcher> matcher = new ArrayList<>();
		matcher.add(new AntPathRequestMatcher("/users/login", HttpMethod.POST.name()));
		matcher.add(new AntPathRequestMatcher("/register/student", HttpMethod.POST.name()));
		matcher.add(new AntPathRequestMatcher("/file/**", HttpMethod.GET.name()));
		return matcher;
	}
	
	@Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                    .allowedOrigins("http://localhost:4200")
                    .allowedMethods(HttpMethod.GET.name(),
                            HttpMethod.POST.name(),
                            HttpMethod.PUT.name(),
                            HttpMethod.DELETE.name(),
                            HttpMethod.PATCH.name());
                    }
            };
        };
}
