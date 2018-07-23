package cn.ff.auth.service.feign;

import cn.ff.common.entity.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

public class UserServiceFallbackImpl{}

/*@Service
public class UserServiceFallbackImpl implements UserFeign{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public SysUser findUserByUsername(String username) {
        logger.error("调用{}异常:{}", "user.findUserByUsername", username);
        return null;
    }
}*/
