package cn.ff.auth.controller;

import cn.ff.auth.service.feign.UserFeign;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
@RestController
@RequestMapping("rest")
public class RestfulController {

    /*@Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserFeign userFeign;

    @GetMapping("call")
    @HystrixCommand(fallbackMethod = "callOgFallback")
    public String callOg(String id) {
        return restTemplate.getForObject("http://user/test/og/" + id,String.class);
    }



    public String callOgFallback(String id){
        return "调用User方法错误， 可能是崩了也可能是找不到  " + id ;
    }
    @GetMapping("calc")
    public String getAuth(int num){
        return  "调用Auth结果： " + (233 + num);

    }*/
}
