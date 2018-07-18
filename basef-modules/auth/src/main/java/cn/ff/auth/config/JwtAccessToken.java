package cn.ff.auth.config;

import cn.ff.auth.service.impl.UserDetailsImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import sun.security.util.SecurityConstants;

import java.util.Map;

public class JwtAccessToken extends JwtAccessTokenConverter {


    /**
     * 生成token
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        DefaultOAuth2AccessToken defaultOAuth2AccessToken = new DefaultOAuth2AccessToken(accessToken);
        // 设置额外用户信息
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        user.setPassword(null);
        // 将用户信息添加到token额外信息中
        defaultOAuth2AccessToken.getAdditionalInformation().put("userInfo", user);
        return super.enhance(defaultOAuth2AccessToken, authentication);
    }

    /**
     * 解析token
     */
    @Override
    public OAuth2AccessToken extractAccessToken(String value, Map<String, ?> map){
        OAuth2AccessToken oauth2AccessToken = super.extractAccessToken(value, map);
        convertData(oauth2AccessToken, oauth2AccessToken.getAdditionalInformation());
        return oauth2AccessToken;
    }

    private void convertData(OAuth2AccessToken accessToken,  Map<String, ?> map) {
        accessToken.getAdditionalInformation().put("userInfo",convertUserData(map.get("userInfo")));

    }

    private UserDetailsImpl convertUserData(Object map) {
        //TODO ： 做一个util map 转 实体类

        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        String json = gson.toJson(map);
        UserDetailsImpl user = gson.fromJson(json, UserDetailsImpl.class);
        return user;
    }



}
