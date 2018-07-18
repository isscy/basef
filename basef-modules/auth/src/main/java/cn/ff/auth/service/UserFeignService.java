package cn.ff.auth.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user")
public interface UserFeignService {

    @GetMapping("/test/og/{id}")
    String getOgById(@PathVariable("id") String id);
}
