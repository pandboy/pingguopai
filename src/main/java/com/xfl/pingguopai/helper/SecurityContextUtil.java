package com.xfl.pingguopai.helper;

import com.xfl.pingguopai.model.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by timely on 2017/9/9.
 */
public class SecurityContextUtil {
    public static User logingUser () {
        return (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
