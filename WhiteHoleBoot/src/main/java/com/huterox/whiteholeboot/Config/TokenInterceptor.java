package com.huterox.whiteholeboot.Config;

import com.alibaba.fastjson.JSON;

import com.huterox.whiteholeboot.utils.MessageVo.TokePassJson.TokenPassJson;
import com.huterox.whiteholeboot.utils.TokenProccessor.TokenProccessor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler)throws Exception{
        if(request.getMethod().equals("OPTIONS")){
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("token");
        if(token == null){
            return true;
        }
        String result = TokenProccessor.verify(token);

        if(!result.equals("0") || !result.equals("2")){
            return true;
        }


        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try{
            TokenPassJson json = new TokenPassJson();
            json.setSuccess(result);//0表示没有携带token,2表示过期
            response.getWriter().append(JSON.toJSONString(json));
        }catch (Exception e){
            e.printStackTrace();
            response.sendError(500);
            return false;
        }
        return false;
    }
}
