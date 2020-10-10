package com.teskinfly.interceptor;

import com.teskinfly.controller.UserController;
import com.teskinfly.pojo.ReturnCode;
import com.teskinfly.service.IUserService;
import com.teskinfly.service.impl.UserService;
import com.teskinfly.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MyInterceptor implements HandlerInterceptor {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    JWTUtils jwtUtils;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }
        String authorization = request.getHeader("Authorization");
//        try {
//            System.out.println(authorization);
//            jwtUtils.parse(authorization);
//        }catch (Exception e) {
//            return false;
        if (redisTemplate.opsForValue().get(authorization) != null) {
            redisTemplate.expire(authorization,15, TimeUnit.MINUTES);
            return true;
        }
        return false;
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
