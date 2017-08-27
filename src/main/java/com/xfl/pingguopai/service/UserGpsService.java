package com.xfl.pingguopai.service;
import com.xfl.pingguopai.model.UserGps;
import com.xfl.pingguopai.common.AbstractService;


/**
 * Created by timely
 */
public interface UserGpsService extends AbstractService<UserGps, Long> {

    UserGps findLatestGPS(Integer userId);
}
