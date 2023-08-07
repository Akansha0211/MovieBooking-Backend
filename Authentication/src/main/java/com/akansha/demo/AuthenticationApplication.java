package com.akansha.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;


import com.akansha.demo.filter.JWTFilter;


@SpringBootApplication
public class AuthenticationApplication {

	@Bean
	public FilterRegistrationBean<JWTFilter> jwtFilter() {
		FilterRegistrationBean<JWTFilter> fb = new FilterRegistrationBean<>();
		
		fb.setFilter(new JWTFilter());
		fb.setOrder(Ordered.HIGHEST_PRECEDENCE);
		// putting restriction on URL
		fb.addUrlPatterns("/api/v1/*"); // authentication api(register and login they can be accessed without any token)
		fb.addInitParameter("exclusions", "/auth/v1/*");
		return fb;
	}
	public static void main(String[] args) {
		SpringApplication.run(AuthenticationApplication.class, args);
	}

}
