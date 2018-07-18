package cn.ff.auth.mapper;

import cn.ff.auth.entity.SysUser;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserMapper {

    SysUser getByUsername(String username);
}
