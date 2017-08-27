package com.xfl.pingguopai.common;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.exceptions.TooManyResultsException;
import tk.mybatis.mapper.entity.Condition;

import java.io.Serializable;
import java.util.List;

/**
 * 共同业务层基础接口
 */
public interface AbstractService<T, PK extends Serializable> {
    void save(T model);//持久化
    void save(List<T> models);//批量持久化
    void deleteById(PK id);//通过主鍵刪除
    void deleteByIds(String ids);//批量刪除 eg：ids -> “1,2,3,4”
    void update(T model);//更新
    T findById(PK id);//通过ID查找
    T findBy(String fieldName, Object value) throws TooManyResultsException; //通过Model中某个成员变量名称（非数据表中column的名称）查找,value需符合unique约束
    List<T> findByIds(String ids);//通过多个ID查找//eg：ids -> “1,2,3,4”
    List<T> findByCondition(Condition condition);//根据条件查找
    List<T> findAll();//获取所有

    PageInfo<T> selectPage(Condition condition, Page page);

    List<T> selectByPage(Condition condition, Page page);
}
