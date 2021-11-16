package com.test.webconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{
	
	@Autowired
	private JwtTokenStore tokenStore;
	
	private static final String[] PUBLIC = { "/oauth/token" };
	
	private static final String[] OPERATOR_OR_ADMIN = { "/users/**" };
	
	private static final String[] ADMIN = { "/users/**" };

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		 
		http.authorizeRequests()
		.antMatchers(HttpMethod.POST, PUBLIC).permitAll()//Permite todas as rotas do vetor public
		.antMatchers(HttpMethod.GET, OPERATOR_OR_ADMIN).hasAnyRole("OPERATOR", "ADMIN")//Permite todas as rotas do vetor OPERATOR_OR_ADMIN para os usuários OPERATOR e ADMIN
		.antMatchers(ADMIN).hasRole("ADMIN")// . . .
		.anyRequest().authenticated();// Permite qualquer outra rota para quem estiver logado
	}

	
}
