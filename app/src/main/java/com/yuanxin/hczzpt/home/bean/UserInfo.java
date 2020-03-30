package com.yuanxin.hczzpt.home.bean;

public class UserInfo {

    /**
     * token : 请求头Jwt-Token参数
     * refresh : 请求头Jwt-Refresh参数
     * user_id : 账户ID
     * user_name : 账户名称
     * last_login_time : 上一次登录时间
     */

    private String token;
    private String refresh;
    private String user_id;
    private String user_name;
    private String last_login_time;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefresh() {
        return refresh;
    }

    public void setRefresh(String refresh) {
        this.refresh = refresh;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(String last_login_time) {
        this.last_login_time = last_login_time;
    }
}
