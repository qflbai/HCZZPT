package com.yuanxin.hczzpt.home.bean;

public class ZpjlTpInfo {
    private String iamgeUrl;
    private   CriminalSuspectDetailsInfo.ExtBean extBean;

    public CriminalSuspectDetailsInfo.ExtBean getExtBean() {
        return extBean;
    }

    public void setExtBean(CriminalSuspectDetailsInfo.ExtBean extBean) {
        this.extBean = extBean;
    }

    public String getIamgeUrl() {
        return iamgeUrl;
    }

    public void setIamgeUrl(String iamgeUrl) {
        this.iamgeUrl = iamgeUrl;
    }
}
