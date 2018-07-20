package cn.ff.auth.config;


import cn.ff.auth.service.impl.UserDetailServiceImpl;
import cn.ff.auth.service.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import sun.security.util.SecurityConstants;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {


    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisConnectionFactory connectionFactory;
    @Autowired
    private UserDetailServiceImpl userDetailServiceImpl;

    @Bean
    public RedisTokenStore tokenStore() {
        return new RedisTokenStore(connectionFactory);
    }

    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(
                Arrays.asList(tokenEnhancer(), jwtAccessTokenConverter()));

        endpoints
                .tokenEnhancer(tokenEnhancerChain)
                .authenticationManager(authenticationManager)
                //.accessTokenConverter(jwtAccessTokenConverter())
                .userDetailsService(userDetailServiceImpl);//若无，refresh_token会有UserDetailsService is required错误
                //.tokenStore(tokenStore());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .allowFormAuthenticationForClients()
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
            .withClient("web")
            .scopes("all")
            .secret("$2a$10$V7WXdvGiR0t3U4IKzKGayuJdI3n/8LuH/KTO/1GnSwoQJOc6rFJ/u")// webApp
            .authorizedGrantTypes("password", "authorization_code", "refresh_token");

    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter  jwtAccessTokenConverter = new JwtAccessToken();
        jwtAccessTokenConverter.setSigningKey("sIgN_kEy");
        return jwtAccessTokenConverter;
    }




    /**
     * jwt 生成token 定制化处理
     *
     * @return TokenEnhancer
     */
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            final Map<String, Object> additionalInfo = new HashMap<>(2);
            additionalInfo.put("version", "0.9");
            UserDetailsImpl user = (UserDetailsImpl) authentication.getUserAuthentication().getPrincipal();
            if (user != null) {
                additionalInfo.put("userId", user.getUsername());
            }
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
            return accessToken;
        };
    }


}
