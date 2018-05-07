package com.kingsoft.lcgl.core.webservice.handle;

import com.kingsoft.lcgl.business.api.admin.service.AdminService;
import com.kingsoft.lcgl.core.webservice.authority.Authority;
import com.kingsoft.lcgl.core.webservice.exception.RestCommonException;
import com.kingsoft.lcgl.core.webservice.service.TokenService;
import com.kingsoft.lcgl.core.webservice.support.ExceptionMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 拦截器
 * Created by yangdiankang on 2017/12/20.
 */
public class WebHandlerInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(WebHandlerInterceptor.class);
    @Autowired
    private AdminService adminService;
    @Autowired
    private TokenService tokenService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        logger.info(" ----------------- :输入： input log start -----------------");
        info(request);
        logger.info(" ----------------- :输入： input log end -----------------");

        //  权限拦截控制模块
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Authority method = handlerMethod.getMethodAnnotation(Authority.class);
        if(method!=null){
           if(method.value().equals("admin")){
               checkAdminAuthority(request,response);
           }else if (method.value().equals("checkToken")){
               checkToken(request);
           }
        }
        return true;


    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    /**
     * 检验admin 权限
     * @param request
     * @param response
     */
    private void checkAdminAuthority(HttpServletRequest request, HttpServletResponse response) {
        adminService.checkAdminAuthority(request,response);
    }


    /**
     *检验token
     * @param
     * @param request
     */
    private void checkToken(HttpServletRequest request){
        tokenService.checkToken(request);
    }
    private void info(HttpServletRequest request) {
        // Address
        StringBuilder addressBuilder = new StringBuilder("地址：: ");
        addressBuilder.append(request.getScheme()).append("://");
        addressBuilder.append(request.getLocalAddr());
        if (request.getLocalPort() != 0) {
            addressBuilder.append(":").append(request.getLocalPort());
        }
        addressBuilder.append(request.getRequestURI());
        if (request.getMethod().equals("GET") && !StringUtils.isEmpty(request.getQueryString())) {
            addressBuilder.append("?").append(request.getQueryString());
        }
        logger.info(addressBuilder.toString());



        // IP
        String ip = request.getHeader("x-real-ip");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        logger.info("IP：: "+ip);
    }

}
