package com.xfl.pingguopai.util;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by timely on 2017/9/4.
 */
@Component
public class DozerUtil {
    @Autowired(required = false)
    private DozerBeanMapper mapper;

    @SuppressWarnings("unchecked")
    public <P> P clone(P base) {
        if (base == null) {
            return null;
        } else {
            return (P) mapper.map(base, base.getClass());
        }

    }

    public <P> List<P> cloneList(List<P> baseList) {

        if (baseList == null) {
            return null;
        } else {
            List<P> targetList = new ArrayList<P>();
            for (P p : baseList) {

                targetList.add((P) clone(p));
            }
            return targetList;
        }

    }

    @SuppressWarnings("unchecked")
    public <V, P> P convert(V base, P target) {

        if (base != null) {
            mapper.map(base, target);
            return target;
        }
        return target;
    }

    @SuppressWarnings("unchecked")
    public <V, P> P convert(V base, Class<P> target) {
        if (base == null) {
            return null;
        } else {
            P p = (P) mapper.map(base, target);
            return p;
        }

    }

    public <V, P> List<P> convertList(List<V> baseList, Class<P> target) {

        if (baseList == null) {
            return null;
        } else {
            List<P> targetList = new ArrayList<P>();
            for (V vo : baseList) {

                targetList.add((P) convert(vo, target));
            }
            return targetList;
        }

    }
}
