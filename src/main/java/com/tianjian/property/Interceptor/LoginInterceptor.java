package com.tianjian.property.Interceptor;

import com.alibaba.fastjson.JSON;

import com.tianjian.property.utils.LockConstants;
import com.tianjian.property.utils.LockResult;
import com.tianjian.property.utils.error.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @description:
 * @author: ManolinCoder
 * @time: 2021/6/24
 */
//
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
        String url = request.getRequestURI();
        boolean loginURL =url.contains("/wechat/login");
        if (!loginURL){
            //获取请求头的token信息
            String token = request.getHeader("token");
            //没有获取到token信息就返回没有token错误
            if (token==null){
                LockResult lockResult = new LockResult();
                lockResult.setCode(ErrorEnum.TOKEN_MISS.getCode());
                lockResult.setErrorMessage(ErrorEnum.TOKEN_MISS.getErrorMsg());
                lockResult.setData(null);
                writeToResponse(response,lockResult);
                return false;
            }
            //判断redis里的token是否存在
            Boolean aBoolean = redisTemplate.hasKey(LockConstants.USER_TOKEN + token);
            Boolean bBoolean = redisTemplate.hasKey(LockConstants.WEB_USER_TOKEN + token);
            if (!aBoolean){
                if (bBoolean){
                    return true;
                }
                LockResult lockResult = new LockResult();
                lockResult.setCode(ErrorEnum.TOKEN_EXPIRE.getCode());
                lockResult.setErrorMessage(ErrorEnum.TOKEN_EXPIRE.getErrorMsg());
                lockResult.setData(null);
                writeToResponse(response,lockResult);
                return false;
            }
            }
        return true;

    }
    //将错误信息返回给web端
    private void writeToResponse(HttpServletResponse response, LockResult result) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        String resultJson = JSON.toJSONString(result);
        PrintWriter writer = response.getWriter();
        writer.write(resultJson);
        writer.close();
    }

}
