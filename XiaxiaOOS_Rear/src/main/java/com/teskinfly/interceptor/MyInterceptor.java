package com.teskinfly.interceptor;

import com.teskinfly.controller.UserController;
import com.teskinfly.pojo.ReturnCode;
import com.teskinfly.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
public class MyInterceptor implements HandlerInterceptor {
    @Autowired
    JWTUtils jwtUtils;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse resp, Object handler) throws Exception {
        String authorization = request.getHeader("Authorization");
        try {
//            System.out.println(authorization);
            jwtUtils.parse(authorization);
        }catch (Exception e) {
//            System.out.println("token 错误");
//            resp.setCharacterEncoding("utf-8");
//            resp.setContentType("application/json; charset=utf-8");
//            PrintWriter writer = resp.getWriter();
//            writer.write("Token error");
            return false;
        }
        return true;

    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
