package com.cubes.stock;

import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@KeycloakConfiguration
public class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		KeycloakAuthenticationProvider keyCloakAuthProvider = keycloakAuthenticationProvider();
		keyCloakAuthProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());

		auth.authenticationProvider(keyCloakAuthProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		super.configure(http);
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.sessionAuthenticationStrategy(sessionAuthenticationStrategy()).and().authorizeRequests()
				.antMatchers("/hello*").hasRole("user").anyRequest().permitAll();
		http.cors().and().csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	}

	@Override
	protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
		return new NullAuthenticatedSessionStrategy();
	}

}
