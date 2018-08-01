package com.tg.demo.shiroapi.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class LoginController{

    public static final String RESULT = "result";
    public static final String MESSAGE = "message";
    @RequestMapping("/login")
    public JSONObject login(String userName,String password){
        JSONObject jsonObject = new JSONObject();
        if(StringUtils.isNoneEmpty(userName,password)) {
            UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession();
            System.out.println(session.getId());

            try {
                subject.login(token);
                jsonObject.put("token", session.getId());
                jsonObject.put(RESULT, 1);
                jsonObject.put(MESSAGE, "登录成功");
            } catch (AuthenticationException e) {
                jsonObject.put(RESULT, 0);
                jsonObject.put(MESSAGE, "登录失败");
                e.printStackTrace();
            } catch (Exception e) {
                jsonObject.put(RESULT, 0);
                jsonObject.put(MESSAGE, "登录失败");
                e.printStackTrace();
            }
        }else{
            jsonObject.put(RESULT,0);
            jsonObject.put(MESSAGE,"登录失败");
        }

        return jsonObject;
    }

    @RequestMapping("/loginPrevent")
    public JSONObject needLogin(){
        JSONObject jsonObject = new JSONObject();

        jsonObject.put(RESULT,0);
        jsonObject.put(MESSAGE,"没有登录");
        return jsonObject;
    }


}
