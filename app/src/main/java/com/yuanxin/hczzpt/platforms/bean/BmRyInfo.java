package com.yuanxin.hczzpt.platforms.bean;

import java.util.List;

public class BmRyInfo {

    private List<DepartBean> depart;
    private List<UserBean> user;
    private List<ListBean> list;
    private List<TypeBean> type;

    public List<DepartBean> getDepart() {
        return depart;
    }

    public void setDepart(List<DepartBean> depart) {
        this.depart = depart;
    }

    public List<UserBean> getUser() {
        return user;
    }

    public void setUser(List<UserBean> user) {
        this.user = user;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public List<TypeBean> getType() {
        return type;
    }

    public void setType(List<TypeBean> type) {
        this.type = type;
    }

    public static class DepartBean {
        /**
         * id : 1
         * number_id : 200227737
         * name : 环南派出所
         * allman : 2
         * created_time : 2020-02-27 10:25:41
         */

        private String id;
        private String number_id;
        private String name;
        private String allman;
        private String created_time;

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

        public String getAllman() {
            return allman;
        }

        public void setAllman(String allman) {
            this.allman = allman;
        }

        public String getCreated_time() {
            return created_time;
        }

        public void setCreated_time(String created_time) {
            this.created_time = created_time;
        }
    }

    public static class UserBean {
        /**
         * id : 2
         * number_id : 200227852
         * system_id : 172717
         * name : 何文胜
         * created_time : 2020-02-27 10:34:42
         */

        private String id;
        private String number_id;
        private String system_id;
        private String name;
        private String created_time;

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

        public String getSystem_id() {
            return system_id;
        }

        public void setSystem_id(String system_id) {
            this.system_id = system_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCreated_time() {
            return created_time;
        }

        public void setCreated_time(String created_time) {
            this.created_time = created_time;
        }
    }

    public static class ListBean {
        /**
         * id : 8
         * user_id : 1
         * user_role : 0
         * is_depart : 3
         * type : 0
         */

        private String id;
        private String user_id;
        private String user_role;
        private String is_depart;
        private String type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUser_role() {
            return user_role;
        }

        public void setUser_role(String user_role) {
            this.user_role = user_role;
        }

        public String getIs_depart() {
            return is_depart;
        }

        public void setIs_depart(String is_depart) {
            this.is_depart = is_depart;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class TypeBean {
        /**
         * id : 4
         * name : 情报协作
         * status : 10
         */

        private String id;
        private String name;
        private String status;
        private String selectText;

        public String getSelectText() {
            return selectText;
        }

        public void setSelectText(String selectText) {
            this.selectText = selectText;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
