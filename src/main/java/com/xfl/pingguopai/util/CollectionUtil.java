package com.xfl.pingguopai.util;

import java.util.*;

/**
 * Created by timely on 2017/9/5.
 */
public class CollectionUtil {
    /**
     * 分组依据，用于集合分组时，获取分组依据
     */
    public interface GroupBy<T> {
        T groupby(Object obj);
    }

    public interface GroupSumBy<T> {
        T groupby(Object obj);

        int value(Object obj);
    }

    /**
     *
     * @param colls
     * @param gb
     * @return
     */
    public static final <T extends Comparable<T>, D> Map<T, List<D>> group(
            Collection<D> colls, GroupBy<T> gb) {
        if (colls == null || colls.isEmpty() || gb == null) {
            return Collections.emptyMap();
        }
        Map<T, List<D>> map = new HashMap<T, List<D>>();
        for (D d : colls) {
            T t = gb.groupby(d);
            if (!map.containsKey(t)) {
                map.put(t, new ArrayList<D>());
            }
            map.get(t).add(d);
        }
        return map;
    }
    public static final <T extends Comparable<T>, D> Map<T, Integer> groupSum(
            Collection<D> colls, GroupSumBy<T> gb) {
        if (colls == null || colls.isEmpty() || gb == null) {
            return Collections.emptyMap();
        }
        Map<T, Integer> map = new HashMap<T, Integer>();
        for (D d : colls) {
            T t = gb.groupby(d);
            Integer value = gb.value(d);
            if (!map.containsKey(t)) {
                map.put(t, value);
            }else{
                map.put(t, map.get(t)+value);
            }

        }
        return map;
    }

    /**
     * 将List转为HashMap，当分组依据包含多个值时，只取第一个
     *
     */
    public static final <T extends Comparable<T>, D> Map<T, D> list2map(
            Collection<D> colls, GroupBy<T> gb) {
        if (colls == null || gb == null) {
            return Collections.emptyMap();
        }
        Map<T, D> map = new HashMap<T, D>();
        for (D d : colls) {
            T t = gb.groupby(d);
            if (!map.containsKey(t)) {
                map.put(t, d);
            }
        }
        return map;
    }

    /**
     * 从EntityList中提取某个字段集合
     *
     */
    public static final <T extends Comparable<T>, D> Set<T> getFieldList(
            Collection<D> colls, GroupBy<T> gb) {
        if (colls == null || gb == null) {
            return Collections.emptySet();
        }
        Set<T> set = new HashSet<T>();
        for (D d : colls) {
            T t = gb.groupby(d);
            set.add(t);
        }
        return set;
    }

}

