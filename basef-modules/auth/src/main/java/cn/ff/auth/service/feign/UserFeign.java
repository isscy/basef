package cn.ff.auth.service.feign;

import cn.ff.common.entity.SysUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient(name = "user"/*, fallback = UserServiceFallbackImpl.class*/)
//@RequestMapping("/user")
public interface UserFeign {



    /**
     * 通过用户名查询用户和角色信息
     */
    @GetMapping("/userInfo/findUserByUsername/{username}")
    SysUser findUserByUsername(@PathVariable("username") String username);

}
