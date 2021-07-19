package com.smallbell.teambition.qa.interceptor;

import com.smallbell.teambition.common.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //拿到请求头
        String header = request.getHeader("Authorization");
        //请求头不为空才对其进行解析
        if(header != null && !"".equals(header)){
            //如果包含有Bearer + 空格才取得token
            if(header.startsWith("Bearer ")){
                //从请求头第7位开始截取取得token令牌
                String token = header.substring(7);
               //此处过期时间到期可能会出异常
                try {
                    //对令牌进行解析验证
                    Claims claims = jwtUtil.parseJWT(token);
                    //获得角色类型
                    String roles = (String) claims.get("roles");
                    //判断角色是admin还是user
                    if(roles != null && roles.equals("admin")){
                        request.setAttribute("claims_admin", token);
                    } else if(roles != null && roles.equals("user")){
                        request.setAttribute("claims_user", token);
                    }
                } catch (Exception e){
                    throw new RuntimeException("令牌不正确！");
                }
            }
        }
        return true;
    }
}
