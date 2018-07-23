package cn.ff.user.service;

import cn.ff.common.entity.SysRole;
import cn.ff.common.entity.SysUser;
import cn.ff.user.mapper.UserInfoMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;


    public SysUser findUserByUsername(String username){
        SysUser user = userInfoMapper.findUserByUsername(username);
        if (user == null || StringUtils.isBlank(user.getId())){
            return null;
        }
        List<SysRole> roles = userInfoMapper.findRoleByUserId(user.getId());
        user.setRoles(roles);
        return user;

    }

}
