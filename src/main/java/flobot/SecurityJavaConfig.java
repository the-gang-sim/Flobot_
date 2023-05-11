package flobot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityJavaConfig {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) 
			throws Exception {
		httpSecurity.cors().disable() //cors방지
					.csrf().disable() //csrf방지
					.formLogin().disable()
					.headers().frameOptions().disable();
		return httpSecurity.build();
	}
}