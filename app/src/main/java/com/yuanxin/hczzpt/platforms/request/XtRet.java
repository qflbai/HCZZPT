package com.yuanxin.hczzpt.platforms.request;

import java.util.List;

public class XtRet {

    /**
     * case_id :
     * depart : [{"id":"","type":""}]
     * user : [{"id":"","type":""}]
     */

  //  private String case_id;
    private List<DepartBean> depart;
    private List<UserBean> user;



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

    public static class DepartBean {
        /**
         * id :
         * type :
         */

        private String id;
        private String type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class UserBean {
        /**
         * id :
         * type :
         */

        private String id;
        private String type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
