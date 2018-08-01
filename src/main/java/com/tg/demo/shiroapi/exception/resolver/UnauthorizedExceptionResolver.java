package com.tg.demo.shiroapi.exception.resolver;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class UnauthorizedExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

        JSONObject object = new JSONObject();
        object.put("result",0);
        object.put("message","没有权限");
        String result = object.toJSONString();
        try {
            httpServletResponse.getOutputStream().write(result.getBytes("UTF-8"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        log.error("没有权限.{}",httpServletRequest.getRequestURI());
        return null;
    }
}
