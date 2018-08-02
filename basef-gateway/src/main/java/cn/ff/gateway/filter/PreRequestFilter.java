package cn.ff.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class PreRequestFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(PreRequestFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;///int值来定义过滤器的执行顺序，数值越小优先级越高
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("ffffffffffffffffffffffffffffffffffffffffffffffffffff");
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        logger.warn(authentication.isAuthenticated() + " " + authentication.getAuthorities().toString());
        logger.info("send {} request to {}",request.getMethod(),request.getRequestURL().toString());
        return null;
    }
}
