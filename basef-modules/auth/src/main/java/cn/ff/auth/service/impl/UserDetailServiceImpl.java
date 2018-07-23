package cn.ff.auth.service.impl;

import cn.ff.auth.service.feign.UserFeign;
import cn.ff.common.constants.SecurityConstant;
import cn.ff.common.entity.SysRole;
import cn.ff.common.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailServiceImpl")
public class UserDetailServiceImpl implements UserDetailsService {
    //    @Autowired
//    private SysUserService sysUserService;
    @Autowired
    private UserFeign userFeign;

    @Override
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {

        //SysUser user = sysUserService.getByUsername(username);
        SysUser user = userFeign.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        List<SysRole> roles = new ArrayList<>();

        SysRole role = new SysRole();
        role.setCode("ROLE_USER");
        roles.add(role);
        user.setRoles(roles);
        user.setStatus(SecurityConstant.STATUS_NORMAL);
        return new UserDetailsImpl(user);
    }
}
