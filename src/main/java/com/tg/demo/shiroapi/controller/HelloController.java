package com.tg.demo.shiroapi.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by tg on 2018/5/27.
 *
 */
@Slf4j
@RestController
public class HelloController {

    @RequestMapping("/hello")
//    @RequiresRoles("admin")
    public String hello(HttpServletRequest request){

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message","hello RestController");
        jsonObject.put("random",(int)(Math.random()*100));
        return jsonObject.toJSONString();
    }

    @RequestMapping("/helloJson")
    @RequiresPermissions("sys:hello")
    public JSONObject helloJson(){
        JSONObject result = new JSONObject();
        result.put("zh_ch","中文");
        result.put("name","张三");
        result.put("size","200");
        return result;
    }

}
