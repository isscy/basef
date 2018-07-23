package cn.ff.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan(value = "cn.ff.user")
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

    /*@Bean
    public AlwaysSampler defaultSampler(){
        return new AlwaysSampler();
    }*/

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
