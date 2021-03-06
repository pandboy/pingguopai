package com.xfl.pingguopai.security.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xfl.pingguopai.common.Result;
import com.xfl.pingguopai.common.ResultCode;
import com.xfl.pingguopai.model.User;
import com.xfl.pingguopai.security.TokenHelper;
import com.xfl.pingguopai.service.CacheHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by timely
 */
@Component
public class LogoutSuccess implements LogoutSuccessHandler {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private CacheHelper cacheHelper;
    @Autowired
    TokenHelper tokenHelper;
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        String authToken = tokenHelper.getToken(httpServletRequest);
        if (authToken != null) {
            // get username from token
            String username = tokenHelper.getUsernameFromToken(authToken);
            if (StringUtils.isNotBlank(username)) {
                cacheHelper.removeSession("session_" + username);
            }
        }
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        response.setContentType("application/json");
        response.getWriter().write( objectMapper.writeValueAsString( result ) );
        response.setStatus(HttpServletResponse.SC_OK);

    }

}