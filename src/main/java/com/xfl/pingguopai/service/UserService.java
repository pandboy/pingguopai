package com.xfl.pingguopai.service;
import com.github.pagehelper.PageInfo;
import com.xfl.pingguopai.helper.OrderSO;
import com.xfl.pingguopai.model.User;
import com.xfl.pingguopai.common.AbstractService;
import com.xfl.pingguopai.vo.UserVO;


/**
 * Created by pandboy
 */
public interface UserService  extends AbstractService<User, Long> {

    PageInfo<UserVO> getUserOrderDistanceList(OrderSO so);
}
