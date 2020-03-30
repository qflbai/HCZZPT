package com.yuanxin.hczzpt.common.bean;

import java.util.List;

public class PageInfo {

    /**
     * list : [{"id":"序号ID","number_id":"对象ID","name":"对象名称","id_card":"对象身份证号","id_wechat":"对象网络号","tag":"对象标签"}]
     * pageCount : 总页数
     * page : 当前页码
     */

    private int pageCount;
    private String page;
    private Object list;

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public Object getList() {
        return list;
    }

    public void setList(Object list) {
        this.list = list;
    }


}
