package cn.ff.gateway.service.impl;

import cn.ff.gateway.filter.PreRequestFilter;
import cn.ff.gateway.service.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
    private static final Logger logger = LoggerFactory.getLogger(PreRequestFilter.class);
    private AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        String requestUrl = request.getRequestURI();
        logger.info("requestUrl:{}",requestUrl);
        List<SimpleGrantedAuthority> grantedAuthorityList = (List<SimpleGrantedAuthority>) authentication.getAuthorities();
        boolean hasPermission = false;

        if (principal != null){
            if (CollectionUtils.isEmpty(grantedAuthorityList)){
                return hasPermission;
            }
            for (SimpleGrantedAuthority authority:grantedAuthorityList
                    ) {
                if (antPathMatcher.match(authority.getAuthority(),requestUrl)){
                    hasPermission = true;
                    break;
                }
            }
        }

        //return hasPermission;
        return true;
    }
}
