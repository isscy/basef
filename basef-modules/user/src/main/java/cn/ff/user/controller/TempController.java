package cn.ff.user.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("test")
@RefreshScope
public class TempController {

    @Autowired
    private RestTemplate restTemplate;



    @Value("${server.port}")
    private String port;

    @Value("${username}")
    private String userName;
    @Value("${password}")
    private String password;
    @Value("${email}")
    private String email;

    @GetMapping("og")
    public String getOg(){
        return "og!" + port;
    }
    @GetMapping("og/{id}")
    public Map getOg(@PathVariable("id") String id){
        Map<String, String> map = new LinkedHashMap<>();
        map.put("userName", userName);
        map.put("password", password);
        map.put("email", email);
        map.put("id", id);
        return map;
    }

    @GetMapping("getNum/{num}")
    @HystrixCommand(fallbackMethod = "callOgFallback")
    public String getNum(@PathVariable("num") Integer num){
        return restTemplate.getForObject("http://localhost:8888/auth/rest/calc?num=" + num, String.class);
    }

    /**
     * fallback
     */
    public String callOgFallback(Integer num){
        return "调用calc方法错误， 可能是崩了也可能是找不到  " + num ;
    }



}
