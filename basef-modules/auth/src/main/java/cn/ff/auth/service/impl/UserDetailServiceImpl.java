package cn.ff.auth.service.impl;

import cn.ff.auth.constants.SecurityConstant;
import cn.ff.auth.entity.SysRole;
import cn.ff.auth.entity.SysUser;
import cn.ff.auth.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailServiceImpl")
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private SysUserService sysUserService;

    @Override
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO
        SysUser user = sysUserService.getByUsername(username);
        if(user == null){
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
