package com.xfl.pingguopai.common;


import com.github.pagehelper.PageInfo;
import com.xfl.pingguopai.helper.PageList;
import com.xfl.pingguopai.helper.PageSO;
import com.xfl.pingguopai.util.DozerUtil;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Condition;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 共同业务实现类
 */
public abstract class AbstractServiceImpl<T, PK extends Serializable> implements AbstractService<T, PK> {

    @Autowired
    protected Mapper<T> mapper;

    @Autowired(required = false)
    protected DozerUtil dozerUtil;

    private Class<T> modelClass;    // 当前泛型真实类型的Class

    public AbstractServiceImpl() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        modelClass = (Class<T>) pt.getActualTypeArguments()[0];
    }

    public int save(T model) {
        if (model instanceof BaseModel) {
            Date date = Calendar.getInstance().getTime();
            BaseModel baseModel = (BaseModel)model;
            baseModel.setCreateTime(date);
            baseModel.setUpdateTime(date);
            model = (T)baseModel;
        }
        return mapper.insertSelective(model);

    }

    public void save(List<T> models) {
        mapper.insertList(models);
    }

    public void deleteById(PK id) {
        mapper.deleteByPrimaryKey(id);
    }

    public void deleteByIds(String ids) {
        mapper.deleteByIds(ids);
    }

    public int update(T model) {
        if (model instanceof BaseModel) {
            Date date = Calendar.getInstance().getTime();
            BaseModel baseModel = (BaseModel)model;
            baseModel.setUpdateTime(date);
            model = (T)baseModel;
        }
        return mapper.updateByPrimaryKeySelective(model);
    }

    public T findById(PK id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public T findBy(String fieldName, Object value) throws TooManyResultsException {
        try {
            T model = modelClass.newInstance();
            Field field = modelClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(model, value);
            return mapper.selectOne(model);
        } catch (ReflectiveOperationException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<T> findByIds(String ids) {
        return mapper.selectByIds(ids);
    }

    public List<T> findByCondition(Condition condition) {
        return mapper.selectByCondition(condition);
    }

    public List<T> findAll() {
        return mapper.selectAll();
    }

    @Override
    public PageList<T> selectPage(Condition condition, PageSO page) {
        RowBounds rowBounds = new RowBounds((page.getPageNum() -1) * page.getPageSize(),
                page.getPageSize());
        List<T> list = mapper.selectByExampleAndRowBounds(condition, rowBounds);
        int count  = mapper.selectCountByCondition(condition);
        PageList pageInfo = new PageList(list, (count/page.getPageSize()) + 1);
        return pageInfo;
    }

    @Override
    public List<T> selectByPage(Condition condition, PageSO page) {
        RowBounds rowBounds = new RowBounds((page.getPageNum() -1) * page.getPageSize(),
                page.getPageSize());
        List<T> list = mapper.selectByExampleAndRowBounds(condition, rowBounds);
        return list;
    }
}
