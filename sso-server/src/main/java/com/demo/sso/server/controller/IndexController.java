package com.demo.sso.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created on 2018/6/13.
 *
 * @author wangxiaodong
 */
@Controller
public class IndexController {

    @Autowired
    /**
     * key -> sessionId
     * value -> username
     */
    private RedisTemplate<String, String> redisTemplate;

    /**
     *  权限校验
     * @param preUrl 来源地址,校验成功后返回地址
     * @param response
     * @param session
     * @throws IOException
     */
     @GetMapping(value = "/check-permission")
    public void checkPermission(String preUrl, HttpServletResponse response, HttpSession session) throws IOException {
         String uuid = (String) session.getAttribute("uuid");
         if(null == uuid || redisTemplate.boundValueOps(uuid).get() == null){
             response.sendRedirect("/login?preUrl="+preUrl);
         }else{
             response.sendRedirect(preUrl+"?uuid="+uuid);
         }
     }

    /**
     * 登陆界面
     * @param preUrl 登陆成功后的跳转页面
     * @return
     */
     @GetMapping(value = "/login")
     public String loginPage(String preUrl, Model model){
         model.addAttribute("preUrl",  preUrl);
         return "login";
     }

    /**
     * 登陆接口
     * @param username
     * @param password
     * @param session
     * @return
     */
     @PostMapping(value = "/api/login")
     @ResponseBody
    public HashMap<String, Object> login(String username, String password, HttpSession session){
         HashMap<String, Object> result = new HashMap<>();
        if("test".equals(username) && "123456".equals(password)){
            String uuid = UUID.randomUUID().toString();
            redisTemplate.boundValueOps(uuid).set(username);
            result.put("code", 200);
            result.put("uuid", uuid);
        }else{
            result.put("code", 500);
            result.put("errMsg", "用户名或密码错误");
        }
        return result;
     }

}
