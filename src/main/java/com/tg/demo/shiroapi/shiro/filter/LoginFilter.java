package com.tg.demo.shiroapi.shiro.filter;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


@Slf4j
public class LoginFilter extends AccessControlFilter {
	final static Class<LoginFilter> CLASS = LoginFilter.class;

	public static final String ACCESS_TOKEN = "Access-Token";

	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		if(isLoginRequest(request, response)){// && isEnabled()
            return Boolean.TRUE;
        }

		Subject subject = getSubject(request, response);
		return subject.isAuthenticated();

	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("login_status", "300");
		jsonObject.put("message", "无效的Token.");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonObject.toJSONString());

		return Boolean.FALSE ;
	}
	

}
