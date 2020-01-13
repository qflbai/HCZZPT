package com.yuanxin.hczzpt.platforms.bean;

/**
 * @author: qflbai
 * @CreateDate: 2020/1/13 17:12
 * @Version: 1.0
 * @description:
 */
public class TeamTitleInfo {
   private String Title;
   private boolean isShowAdd;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public boolean isShowAdd() {
        return isShowAdd;
    }

    public void setShowAdd(boolean showAdd) {
        isShowAdd = showAdd;
    }
}
