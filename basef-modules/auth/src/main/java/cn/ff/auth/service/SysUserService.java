package cn.ff.auth.service;

import cn.ff.auth.entity.SysUser;
import cn.ff.auth.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    public SysUser getByUsername(String username) {
        return sysUserMapper.getByUsername(username);
    }
}
