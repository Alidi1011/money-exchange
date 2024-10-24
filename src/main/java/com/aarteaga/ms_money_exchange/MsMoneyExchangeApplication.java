package com.aarteaga.ms_money_exchange;

import com.aarteaga.ms_money_exchange.security.JWTAuthorizationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@SpringBootApplication
public class MsMoneyExchangeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsMoneyExchangeApplication.class, args);
	}

	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
					.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
					.authorizeRequests()
					//.antMatchers(HttpMethod.POST, "/user").permitAll()
					.antMatchers("/user/**").permitAll()
					.requestMatchers(new AntPathRequestMatcher("/v3/**")).permitAll()
					.requestMatchers(new AntPathRequestMatcher("/swagger-ui/**")).permitAll()
					.anyRequest().authenticated();
		}
	}

}
