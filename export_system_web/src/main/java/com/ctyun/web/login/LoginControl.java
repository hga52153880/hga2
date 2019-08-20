package com.ctyun.web.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author hga
 * @date 2019-07-23 12:13
 */
@Controller
public class LoginControl {
    /**
     * 访问index页面时,请求服务器,跳转到home页面
     * @return
     */
    @RequestMapping("/login")
    public  String login(){
        return "/home/main";
    }
    @RequestMapping("/home")
    public  String home(){
        return "/home/home";
    }
}