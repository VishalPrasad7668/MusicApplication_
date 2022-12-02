package com.niit.UserTrackService;

import com.niit.UserTrackService.filter.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserTrackServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserTrackServiceApplication.class, args);
	}
	@Bean
	FilterRegistrationBean jwtFilter(){
		FilterRegistrationBean frb = new FilterRegistrationBean<>();
		frb.setFilter(new JwtFilter());
		frb.addUrlPatterns("/trackapp/v1/track/*");
		return frb;
	}
}
