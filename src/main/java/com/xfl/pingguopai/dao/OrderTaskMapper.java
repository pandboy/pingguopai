package com.xfl.pingguopai.dao;

import com.xfl.pingguopai.common.Mapper;
import com.xfl.pingguopai.model.OrderTask;
import com.xfl.pingguopai.model.User;
import org.apache.ibatis.annotations.Select;

public interface OrderTaskMapper extends Mapper<OrderTask> {
    @Select("select * from t_order_task where id=#{id}")
    OrderTask findByCustom(Long id);
}