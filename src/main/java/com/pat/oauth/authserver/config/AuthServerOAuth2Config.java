package com.pat.oauth.authserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
@EnableAuthorizationServer
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class AuthServerOAuth2Config extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("crazy_frog")
                .secret("4E78AB46-5188-421A-AB1A-3827CA785538")
                .authorizedGrantTypes("authorization_code", "refresh_token", "implicit")
                .authorities("USER", "ADMIN")
                .scopes("read", "write")
                .autoApprove(false);
    }
}
