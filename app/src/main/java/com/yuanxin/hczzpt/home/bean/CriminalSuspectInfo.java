package com.yuanxin.hczzpt.home.bean;

import java.util.List;

/**
 * @author: qflbai
 * @CreateDate: 2020/1/8 20:02
 * @Version: 1.0
 * @description:
 */
public class CriminalSuspectInfo {
    private int viewType;
    /**
     * id : 序号ID
     * number_id : 对象ID
     * name : 对象名称
     * id_card : 对象身份证号
     * id_wechat : 对象网络号
     * tag : 对象标签
     */

    private String id;
    private String number_id;
    private String name;
    private String id_card;
    private String id_wechat;
    private String tag;

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber_id() {
        return number_id;
    }

    public void setNumber_id(String number_id) {
        this.number_id = number_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public String getId_wechat() {
        return id_wechat;
    }

    public void setId_wechat(String id_wechat) {
        this.id_wechat = id_wechat;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
