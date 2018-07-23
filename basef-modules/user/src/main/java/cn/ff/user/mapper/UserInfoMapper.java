package cn.ff.user.mapper;

import cn.ff.common.entity.SysRole;
import cn.ff.common.entity.SysUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoMapper {

    SysUser findUserByUsername(String username);

    List<SysRole> findRoleByUserId(String userId);

}
