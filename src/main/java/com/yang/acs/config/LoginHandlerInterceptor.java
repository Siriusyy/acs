package com.yang.acs.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object loginuser = request.getSession().getAttribute("loginuser");
        if (loginuser == null) {
            request.setAttribute("msg", "请先登陆！");
            request.getRequestDispatcher("/login").forward(request, response);
            return false;
        } else {

            return true;
        }
    }
}
