package com.teskinfly.interceptor;

import com.teskinfly.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
