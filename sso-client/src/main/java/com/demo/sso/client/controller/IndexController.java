package com.demo.sso.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created on 2018/6/13.
 *
 * @author wangxiaodong
 */
@Controller
public class IndexController {


    @GetMapping(value = "/test")
    @ResponseBody
    public String test(){
        return "你已成功登陆!";
    }

}
