package com.yuanxin.hczzpt.home.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class SdryXx {

    /**
     * info : {"number_id":"200309167","man_name":"来来来","avatar_id":"/Uploads/Images/2020-03-16/IMG_20200316_22374266_23357673.jpeg","id_card":"440103199104180016","id_car":"好好","id_wechat":"刚刚好","phone":"13000000888","case_id":"42","tag":"","action_name":"好好","action_type":"1","action_time":"2020-03-16 22:12:37","action_address":"公关","action_position":"","action_remark":"","action_images":[{"id":"42","name":"IMG_20200316_22375852.jpeg","path":"/Uploads/Images/2020-03-16/IMG_20200316_22375852_40146486.jpeg"}],"state":"10","avatar":""}
     * list : [{"id":"1","name":"抢劫"},{"id":"2","name":"抢夺"},{"id":"3","name":"入室盗窃"}]
     */

    private InfoBean info;
    private List<ListBean> list;

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class InfoBean {

        /**
         * number_id : 200309167
         * man_name : 开口
         * avatar_id : 65
         * id_card : 440103199104180016
         * id_car : 好好
         * id_wechat : 急急急
         * phone : 18866555666
         * case_id : 60
         * tag :
         * action_name : 急急急
         * action_type : 1
         * action_time : 2020-03-31 20:05:40
         * action_address : 嫁接
         * action_position :
         * action_remark : 合集
         * action_images : 65,66,67
         * state : 10
         * avatar :
         * _avatar_id : /Uploads/Images/2020-03-31/IMG_20200331_20390634_90669194.jpeg
         * _action_images : [{"id":"65","name":"IMG_20200331_20390634.jpeg","path":"/Uploads/Images/2020-03-31/IMG_20200331_20390634_90669194.jpeg"},{"id":"66","name":"IMG_20200331_20401868.jpeg","path":"/Uploads/Images/2020-03-31/IMG_20200331_20401868_53320101.jpeg"},{"id":"67","name":"IMG_20200331_20404344.jpeg","path":"/Uploads/Images/2020-03-31/IMG_20200331_20404344_31512211.jpeg"}]
         */

        private String number_id;
        private String man_name;
        private String avatar_id;
        private String id_card;
        private String id_car;
        private String id_wechat;
        private String phone;
        private String case_id;
        private String tag;
        private String action_name;
        private String action_type;
        private String action_time;
        private String action_address;
        private String action_position;
        private String action_remark;
        private String action_images;
        private String state;
        private String avatar;
        private String _avatar_id;
        private List<ActionImagesBean> _action_images;

        public String getNumber_id() {
            return number_id;
        }

        public void setNumber_id(String number_id) {
            this.number_id = number_id;
        }

        public String getMan_name() {
            return man_name;
        }

        public void setMan_name(String man_name) {
            this.man_name = man_name;
        }

        public String getAvatar_id() {
            return avatar_id;
        }

        public void setAvatar_id(String avatar_id) {
            this.avatar_id = avatar_id;
        }

        public String getId_card() {
            return id_card;
        }

        public void setId_card(String id_card) {
            this.id_card = id_card;
        }

        public String getId_car() {
            return id_car;
        }

        public void setId_car(String id_car) {
            this.id_car = id_car;
        }

        public String getId_wechat() {
            return id_wechat;
        }

        public void setId_wechat(String id_wechat) {
            this.id_wechat = id_wechat;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCase_id() {
            return case_id;
        }

        public void setCase_id(String case_id) {
            this.case_id = case_id;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getAction_name() {
            return action_name;
        }

        public void setAction_name(String action_name) {
            this.action_name = action_name;
        }

        public String getAction_type() {
            return action_type;
        }

        public void setAction_type(String action_type) {
            this.action_type = action_type;
        }

        public String getAction_time() {
            return action_time;
        }

        public void setAction_time(String action_time) {
            this.action_time = action_time;
        }

        public String getAction_address() {
            return action_address;
        }

        public void setAction_address(String action_address) {
            this.action_address = action_address;
        }

        public String getAction_position() {
            return action_position;
        }

        public void setAction_position(String action_position) {
            this.action_position = action_position;
        }

        public String getAction_remark() {
            return action_remark;
        }

        public void setAction_remark(String action_remark) {
            this.action_remark = action_remark;
        }

        public String getAction_images() {
            return action_images;
        }

        public void setAction_images(String action_images) {
            this.action_images = action_images;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        @JSONField(name="_avatar_id")
        public String get_avatar_id() {
            return _avatar_id;
        }

        @JSONField(name="_avatar_id")
        public void set_avatar_id(String _avatar_id) {
            this._avatar_id = _avatar_id;
        }

        @JSONField(name="_action_images")
        public List<ActionImagesBean> get_action_images() {
            return _action_images;
        }

        @JSONField(name="_action_images")
        public void set_action_images(List<ActionImagesBean> _action_images) {
            this._action_images = _action_images;
        }

        public static class ActionImagesBean {
            /**
             * id : 65
             * name : IMG_20200331_20390634.jpeg
             * path : /Uploads/Images/2020-03-31/IMG_20200331_20390634_90669194.jpeg
             */

            private String id;
            private String name;
            private String path;

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

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }
        }
    }

    public static class ListBean {
        /**
         * id : 1
         * name : 抢劫
         */

        private String id;
        private String name;

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
    }
}
