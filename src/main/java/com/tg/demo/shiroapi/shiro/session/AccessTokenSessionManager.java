package com.tg.demo.shiroapi.shiro.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public class AccessTokenSessionManager extends DefaultWebSessionManager {

    public static final String ACCESS_TOKEN = "Access-Token";

    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        if(request instanceof HttpServletRequest){
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            String sessionId = httpServletRequest.getHeader(ACCESS_TOKEN);
            if(sessionId!=null) {
                try {
                    Session session = retrieveSessionFromDataSource(sessionId);
                    System.out.println("Access-Token:"+sessionId);
                    return sessionId;
                }catch (Exception e){
                }
            }
        }
        return super.getSessionId(request,response);
    }


}
