package cn.ff.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import sun.net.www.content.image.png;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableResourceServer
//@Order(3)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .authorizeRequests()
                //.antMatchers("/test","/oauth/token").permitAll()
                .antMatchers("/test","/oauth/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }



    /*@Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .requestMatchers().antMatchers("/current")
                .and()
                .authorizeRequests()
                .antMatchers("/current").access("#oauth2.hasScope('read')");
    }*/

    /*@Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .authorizeRequests()
                .antMatchers("/test", "/oauth/token").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }*/


    /*private ClientDetailsService clientDetailsService;

    private static boolean enable_auth;
    private static String resourceId;
    ResourceServerConfig(@Value("${self.oauth.enable}")boolean enable_auth, @Value("${self.oauth.resource}")String resourceId){
        this.enable_auth = enable_auth;
        this.resourceId = resourceId;
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        return tokenEnhancerChain();
    }
    private JwtAccessTokenConverter tokenEnhancerChain() {
        JwtAccessTokenConverter tokenEnhancerChain = new JwtAccessTokenConverter();
        tokenEnhancerChain.setAccessTokenConverter(jwtAccessTokenConverter());
        return tokenEnhancerChain;
    }
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        DefaultUserAuthenticationConverter defaultUserAuthenticationConverter = new DefaultUserAuthenticationConverter();
        defaultUserAuthenticationConverter.setUserDetailsService(userDetailsService);
        DefaultAccessTokenConverter defaultAccessTokenConverter = new DefaultAccessTokenConverter();
        jwtAccessTokenConverter.setAccessTokenConverter(defaultAccessTokenConverter);
        jwtAccessTokenConverter.setSigningKey("yong_secret2");
        return jwtAccessTokenConverter;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {

        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setTokenEnhancer(accessTokenConverter());
        defaultTokenServices.setClientDetailsService(clientDetailsService);
        resources.resourceId(resourceId).tokenServices(defaultTokenServices);
    }*/
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        if(enable_auth) {
//            http.authorizeRequests()
//                    .antMatchers(HttpMethod.GET, "/", "/*.html", "/**/*.css", "/**/*.js", "/**/*.png").permitAll()
//                    .antMatchers("/user/register", "/index", "/v2/api-docs", "/swagger-resources/**").permitAll()
//                    .anyRequest().authenticated().and().csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        }else {
//            http.antMatcher("/**").authorizeRequests().anyRequest().permitAll();
//        }
//    }


}
