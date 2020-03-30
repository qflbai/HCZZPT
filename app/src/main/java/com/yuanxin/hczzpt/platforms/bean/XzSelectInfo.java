package com.yuanxin.hczzpt.platforms.bean;

import java.util.List;

public class XzSelectInfo {
    private String name;
    private Object object;
    private boolean isSelect;
    private List<BmRyInfo.TypeBean> typeBeans;
    private String selectText;
    private BmRyInfo.TypeBean selectType ;

    public BmRyInfo.TypeBean getSelectType() {
        return selectType;
    }

    public void setSelectType(BmRyInfo.TypeBean selectType) {
        this.selectType = selectType;
    }

    public String getSelectText() {
        return selectText;
    }

    public void setSelectText(String selectText) {
        this.selectText = selectText;
    }

    public List<BmRyInfo.TypeBean> getTypeBeans() {
        return typeBeans;
    }

    public void setTypeBeans(List<BmRyInfo.TypeBean> typeBeans) {
        this.typeBeans = typeBeans;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
