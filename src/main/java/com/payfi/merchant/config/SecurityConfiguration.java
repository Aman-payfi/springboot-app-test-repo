package com.payfi.merchant.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;

import com.okta.spring.boot.oauth.Okta;


@EnableWebSecurity
@Configuration
@Order(3)
public class SecurityConfiguration{
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //disable cross-site request forgery
        http.csrf(csrf -> csrf.disable());

        //protect endpoints at /api/<type>/secure
        http.authorizeRequests(configurer ->
                configurer.antMatchers("/merchants/signup").permitAll()
                          .antMatchers("/merchants/**",
                                        "/api/reviews/secure/**",
                                        "/api/messages/secure/**",
                                        "/api/admin/secure/**")
                                        .authenticated()
        ).oauth2ResourceServer(server -> server
                        .jwt());

        //add cors filter to api endpoints
        //http.cors(withDefaults());
		
		//add content negotiation strategy
		http.setSharedObject(ContentNegotiationStrategy.class,
		                     new HeaderContentNegotiationStrategy() );
		
		//force okta a on-emplt response for 401's to make the response friendly
		Okta.configureResourceServer401ResponseBody(http);
		
		return http.build();
	}

}
