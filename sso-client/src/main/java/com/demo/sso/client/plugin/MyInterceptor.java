package com.demo.sso.client.plugin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created on 2018/6/13.
 * 权限验证拦截器
 * @author wangxiaodong
 */
@Component
public class MyInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Value("${sso.server.check-url}")
    private String checkUrl;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("handler开始执行");
        String uuid = request.getParameter("uuid");
        HttpSession session = request.getSession();
        if(uuid == null || uuid.length() == 0){
            System.out.println("url中没有参数");
            uuid = (String) session.getAttribute("uuid");
        }else{
            System.out.println("url中有uuid");
            session.setAttribute("uuid", uuid);
        }
        //判断是否已经登陆
        if(uuid == null || redisTemplate.boundValueOps(uuid).get() == null){
            response.sendRedirect(checkUrl +"?preUrl="+request.getRequestURL().toString());
            System.out.println("uuid不存在或未登录");
            //不往后面执行
            return false;
        }
        System.out.println("已登录");
        return true;
    }
}
