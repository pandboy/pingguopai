package com.xfl.pingguopai.service.impl;

import com.github.pagehelper.Page;
import com.xfl.pingguopai.dao.UserGpsMapper;
import com.xfl.pingguopai.model.UserGps;
import com.xfl.pingguopai.service.UserGpsService;
import com.xfl.pingguopai.common.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by timely
 */
@Service
@Transactional
public class UserGpsServiceImpl extends AbstractServiceImpl<UserGps, Long> implements UserGpsService {
    @Resource
    private UserGpsMapper tUserGpsMapper;

    @Override
    public UserGps findLatestGPS(Integer userId) {
        Condition condition = new Condition(UserGps.class);
        condition.createCriteria().andEqualTo("userId", userId);
        condition.orderBy("createTime").desc();
        Page page = new Page(1,1);
        List<UserGps> userGpsList = selectByPage(condition, page);
        UserGps userGps = null;
        if (!CollectionUtils.isEmpty(userGpsList)) {
            userGps = userGpsList.get(0);
        }
        return userGps;
    }
}
