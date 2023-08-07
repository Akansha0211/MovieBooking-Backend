package com.akansha.movieBooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;

import com.akansha.movieBooking.filter.JWTFilter;


@SpringBootApplication
public class MovieBookingApplication {

//	@Bean
//	public FilterRegistrationBean<JWTFilter> jwtFilter() {
//		FilterRegistrationBean<JWTFilter> fb = new FilterRegistrationBean<>();
//		
//		fb.setFilter(new JWTFilter());
//		fb.setOrder(Ordered.HIGHEST_PRECEDENCE);
//		// putting restriction on URL
//		fb.addUrlPatterns("/api/v1/*"); // authentication api(register and login they can be accessed without any token)
//		fb.addInitParameter("exclusions", "/call/consumer*");		
//		return fb;
//	}
	
	public static void main(String[] args) {
		SpringApplication.run(MovieBookingApplication.class, args);
	}
}
