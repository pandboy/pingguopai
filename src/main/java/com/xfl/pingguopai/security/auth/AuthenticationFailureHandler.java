package com.xfl.pingguopai.security.auth;

import com.alibaba.fastjson.JSON;
import com.xfl.pingguopai.common.Result;
import com.xfl.pingguopai.common.ResultCode;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by timely
 */

@Component
public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        Result result = new Result();
        result.setCode(ResultCode.UNAUTHORIZED);
        String errorMesg = exception.getMessage();
        if (exception instanceof BadCredentialsException) {
            errorMesg = "用户名或密码错误";
        }
        result.setMessage(errorMesg);
        //response.sendError(401, );
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        try {
            response.getWriter().write(JSON.toJSONString(result));
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
        //super.onAuthenticationFailure(request, response, exception);
    }
}