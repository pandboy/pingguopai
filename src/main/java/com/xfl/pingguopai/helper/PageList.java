package com.xfl.pingguopai.helper;

import java.util.ArrayList;
import java.util.List;

public class PageList<T> {
    public static final int DEFAULT_PAGE_SIZE = 50;

    /**
     * 每页的列表
     */
    private List<T> datas;

    /**
     * 总记录数
     */
    private long total;

    public PageList() {

    }

    public PageList(List<T> datas, long total) {
        this.datas = datas;
        this.total = total;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public void appendDatas(T t) {
        if (null == datas) {
            datas = new ArrayList<T>();
        }
        datas.add(t);
    }

}
