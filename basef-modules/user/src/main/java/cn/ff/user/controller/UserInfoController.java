package cn.ff.user.controller;

import cn.ff.common.entity.SysUser;
import cn.ff.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("userInfo")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;


    /**
     * 内部服务调用--> 通过用户名获取用户和角色
     */
    @GetMapping("findUserByUsername/{username}")
    public SysUser findUserByUsername(@PathVariable String username){

        return userInfoService.findUserByUsername(username);

    }
}
