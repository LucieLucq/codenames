package fr.codenames.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/inscription").permitAll().antMatchers("/api/**").permitAll().antMatchers("/css/**").permitAll().antMatchers("/images/**").permitAll().antMatchers("/js/**").permitAll().antMatchers("/**").hasAnyRole("ADMIN", "USER").and()
				.formLogin().loginPage("/connexion").loginProcessingUrl("/perform_login")
				.defaultSuccessUrl("/accueil", true).failureUrl("/connexion?error=true").permitAll().and().logout()
				.logoutUrl("/deconnexion").logoutSuccessUrl("/connexion").permitAll();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
//		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
//		System.out.println(new BCryptPasswordEncoder().encode("Q23uentin"));
		return new BCryptPasswordEncoder();

	}
}
