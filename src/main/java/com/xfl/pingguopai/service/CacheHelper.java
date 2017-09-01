package com.xfl.pingguopai.service;

import com.xfl.pingguopai.common.LocalCache;
import com.xfl.pingguopai.model.User;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Created by timely
 */
@Component
public class CacheHelper {
    private static final long EXPIRE_TIME_ONE_DAY = 24 * 60 * 60 * 1000;

    private static final long MAX_SIZE_NEVER = -1;

    private static final String CACHE_KEY_SPLIT = "#";

    private static LocalCache<String, User> loginSessionCache = new LocalCache<>(
            "", MAX_SIZE_NEVER, EXPIRE_TIME_ONE_DAY
    );

    public User getSessionUser(String token) {
        return loginSessionCache.get(token);
    }

    public void saveSession(String token, User user) {
        Assert.hasText(token, "token 必须输入");
        loginSessionCache.put(token, user);
    }

    public void removeSession(String token) {
        loginSessionCache.remove(token);
    }
}
