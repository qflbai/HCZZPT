package com.yuanxin.hczzpt.home.bean;

import java.util.List;

public class AjInfo {

    /**
     * resultCode : 1
     * data : {"list":[{"id":"49","case_name":"公关","reported_time":null,"case_type":"抢劫","created_man":"超级管理员","created_time":"2020-03-18 07:29:10","case_action":"0","case_state":"10","mlist":"","rlist":""},{"id":"48","case_name":"发","reported_time":null,"case_type":"抢劫","created_man":"超级管理员","created_time":"2020-03-17 03:16:03","case_action":"0","case_state":"10","mlist":"","rlist":"0"},{"id":"47","case_name":"啊啊","reported_time":null,"case_type":"抢劫","created_man":"超级管理员","created_time":"2020-03-17 03:09:25","case_action":"0","case_state":"10","mlist":"31","rlist":"31"},{"id":"46","case_name":"老了","reported_time":null,"case_type":"抢劫","created_man":"超级管理员","created_time":"2020-03-17 03:02:13","case_action":"0","case_state":"10","mlist":"","rlist":""},{"id":"45","case_name":"的","reported_time":null,"case_type":"抢劫","created_man":"超级管理员","created_time":"2020-03-17 02:45:58","case_action":"0","case_state":"10","mlist":"","rlist":""},{"id":"44","case_name":"哈哈哈哈哈哈哈","reported_time":null,"case_type":"抢劫","created_man":"超级管理员","created_time":"2020-03-17 02:40:03","case_action":"0","case_state":"10","mlist":"","rlist":""},{"id":"43","case_name":"QQ飞车党","reported_time":"2020-03-15 14:12:49","case_type":"抢劫","created_man":"超级管理员","created_time":"2020-03-15 06:35:37","case_action":"0","case_state":"10","mlist":"16","rlist":"16"},{"id":"42","case_name":"QQ飞车","reported_time":"2020-03-15 14:07:42","case_type":"抢劫","created_man":"超级管理员","created_time":"2020-03-15 06:28:40","case_action":"0","case_state":"20","mlist":"23","rlist":"23"},{"id":"41","case_name":"啊啊","reported_time":null,"case_type":"抢劫","created_man":"超级管理员","created_time":"2020-03-14 08:30:47","case_action":"0","case_state":"20","mlist":"","rlist":"0"},{"id":"40","case_name":"啊啊","reported_time":null,"case_type":"抢劫","created_man":"超级管理员","created_time":"2020-03-14 08:13:20","case_action":"0","case_state":"10","mlist":"","rlist":"0"},{"id":"39","case_name":"啊啊","reported_time":null,"case_type":"抢劫","created_man":"超级管理员","created_time":"2020-03-14 08:04:50","case_action":"0","case_state":"10","mlist":"","rlist":""},{"id":"38","case_name":"案件","reported_time":null,"case_type":"抢劫","created_man":"超级管理员","created_time":"2020-03-14 07:23:17","case_action":"0","case_state":"10","mlist":"","rlist":""},{"id":"37","case_name":"QQ","reported_time":null,"case_type":"抢劫","created_man":"超级管理员","created_time":"2020-03-14 03:26:37","case_action":"0","case_state":"10","mlist":"","rlist":""},{"id":"36","case_name":"啊啊","reported_time":null,"case_type":"抢劫","created_man":"超级管理员","created_time":"2020-03-14 03:22:10","case_action":"0","case_state":"10","mlist":"","rlist":""},{"id":"35","case_name":"12","reported_time":null,"case_type":"抢劫","created_man":"超级管理员","created_time":"2020-03-14 03:14:35","case_action":"0","case_state":"10","mlist":"","rlist":""}],"pageCount":4,"page":0,"case_type":[{"id":1,"name":"抢劫"},{"id":2,"name":"抢夺"},{"id":3,"name":"入室盗窃"}],"level_role":"0"}
     */

    private int resultCode;
    private DataBean data;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * list : [{"id":"49","case_name":"公关","reported_time":null,"case_type":"抢劫","created_man":"超级管理员","created_time":"2020-03-18 07:29:10","case_action":"0","case_state":"10","mlist":"","rlist":""},{"id":"48","case_name":"发","reported_time":null,"case_type":"抢劫","created_man":"超级管理员","created_time":"2020-03-17 03:16:03","case_action":"0","case_state":"10","mlist":"","rlist":"0"},{"id":"47","case_name":"啊啊","reported_time":null,"case_type":"抢劫","created_man":"超级管理员","created_time":"2020-03-17 03:09:25","case_action":"0","case_state":"10","mlist":"31","rlist":"31"},{"id":"46","case_name":"老了","reported_time":null,"case_type":"抢劫","created_man":"超级管理员","created_time":"2020-03-17 03:02:13","case_action":"0","case_state":"10","mlist":"","rlist":""},{"id":"45","case_name":"的","reported_time":null,"case_type":"抢劫","created_man":"超级管理员","created_time":"2020-03-17 02:45:58","case_action":"0","case_state":"10","mlist":"","rlist":""},{"id":"44","case_name":"哈哈哈哈哈哈哈","reported_time":null,"case_type":"抢劫","created_man":"超级管理员","created_time":"2020-03-17 02:40:03","case_action":"0","case_state":"10","mlist":"","rlist":""},{"id":"43","case_name":"QQ飞车党","reported_time":"2020-03-15 14:12:49","case_type":"抢劫","created_man":"超级管理员","created_time":"2020-03-15 06:35:37","case_action":"0","case_state":"10","mlist":"16","rlist":"16"},{"id":"42","case_name":"QQ飞车","reported_time":"2020-03-15 14:07:42","case_type":"抢劫","created_man":"超级管理员","created_time":"2020-03-15 06:28:40","case_action":"0","case_state":"20","mlist":"23","rlist":"23"},{"id":"41","case_name":"啊啊","reported_time":null,"case_type":"抢劫","created_man":"超级管理员","created_time":"2020-03-14 08:30:47","case_action":"0","case_state":"20","mlist":"","rlist":"0"},{"id":"40","case_name":"啊啊","reported_time":null,"case_type":"抢劫","created_man":"超级管理员","created_time":"2020-03-14 08:13:20","case_action":"0","case_state":"10","mlist":"","rlist":"0"},{"id":"39","case_name":"啊啊","reported_time":null,"case_type":"抢劫","created_man":"超级管理员","created_time":"2020-03-14 08:04:50","case_action":"0","case_state":"10","mlist":"","rlist":""},{"id":"38","case_name":"案件","reported_time":null,"case_type":"抢劫","created_man":"超级管理员","created_time":"2020-03-14 07:23:17","case_action":"0","case_state":"10","mlist":"","rlist":""},{"id":"37","case_name":"QQ","reported_time":null,"case_type":"抢劫","created_man":"超级管理员","created_time":"2020-03-14 03:26:37","case_action":"0","case_state":"10","mlist":"","rlist":""},{"id":"36","case_name":"啊啊","reported_time":null,"case_type":"抢劫","created_man":"超级管理员","created_time":"2020-03-14 03:22:10","case_action":"0","case_state":"10","mlist":"","rlist":""},{"id":"35","case_name":"12","reported_time":null,"case_type":"抢劫","created_man":"超级管理员","created_time":"2020-03-14 03:14:35","case_action":"0","case_state":"10","mlist":"","rlist":""}]
         * pageCount : 4
         * page : 0
         * case_type : [{"id":1,"name":"抢劫"},{"id":2,"name":"抢夺"},{"id":3,"name":"入室盗窃"}]
         * level_role : 0
         */

        private int pageCount;
        private int page;
        private String level_role;
        private List<ListBean> list;
        private List<CaseTypeBean> case_type;

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public String getLevel_role() {
            return level_role;
        }

        public void setLevel_role(String level_role) {
            this.level_role = level_role;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<CaseTypeBean> getCase_type() {
            return case_type;
        }

        public void setCase_type(List<CaseTypeBean> case_type) {
            this.case_type = case_type;
        }

        public static class ListBean {
            /**
             * id : 49
             * case_name : 公关
             * reported_time : null
             * case_type : 抢劫
             * created_man : 超级管理员
             * created_time : 2020-03-18 07:29:10
             * case_action : 0
             * case_state : 10
             * mlist :
             * rlist :
             */

            private String id;
            private String case_name;
            private String reported_time;
            private String case_type;
            private String created_man;
            private String created_time;
            private String case_action;
            private String case_state;
            private String mlist;
            private String rlist;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCase_name() {
                return case_name;
            }

            public void setCase_name(String case_name) {
                this.case_name = case_name;
            }

            public String getReported_time() {
                return reported_time;
            }

            public void setReported_time(String reported_time) {
                this.reported_time = reported_time;
            }

            public String getCase_type() {
                return case_type;
            }

            public void setCase_type(String case_type) {
                this.case_type = case_type;
            }

            public String getCreated_man() {
                return created_man;
            }

            public void setCreated_man(String created_man) {
                this.created_man = created_man;
            }

            public String getCreated_time() {
                return created_time;
            }

            public void setCreated_time(String created_time) {
                this.created_time = created_time;
            }

            public String getCase_action() {
                return case_action;
            }

            public void setCase_action(String case_action) {
                this.case_action = case_action;
            }

            public String getCase_state() {
                return case_state;
            }

            public void setCase_state(String case_state) {
                this.case_state = case_state;
            }

            public String getMlist() {
                return mlist;
            }

            public void setMlist(String mlist) {
                this.mlist = mlist;
            }

            public String getRlist() {
                return rlist;
            }

            public void setRlist(String rlist) {
                this.rlist = rlist;
            }
        }

        public static class CaseTypeBean {
            /**
             * id : 1
             * name : 抢劫
             */

            private int id;
            private String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
