package com.yuanxin.hczzpt.home.bean;

import java.util.List;

public class CriminalSuspectDetailsInfo {

    /**
     * info : {"number_id":"200309167","name":"测试嫌疑人","avatar_id":"/Uploads/Images/2020-03-09/WechatIMG7024_53767697.png","id_card":"440103199104180016","id_car":"粤A73621","id_wechat":"38474273","phone":"18629291848","tag":""}
     * ext : [{"id":"5","action_name":"","action_type":"","action_time":null,"action_address":"","action_position":"","action_remark":"","action_images":","}]
     */

    private InfoBean info;
    private List<ExtBean> ext;

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public List<ExtBean> getExt() {
        return ext;
    }

    public void setExt(List<ExtBean> ext) {
        this.ext = ext;
    }

    public static class InfoBean {
        /**
         * number_id : 200309167
         * name : 测试嫌疑人
         * avatar_id : /Uploads/Images/2020-03-09/WechatIMG7024_53767697.png
         * id_card : 440103199104180016
         * id_car : 粤A73621
         * id_wechat : 38474273
         * phone : 18629291848
         * tag :
         */

        private String number_id;
        private String name;
        private String avatar_id;
        private String id_card;
        private String id_car;
        private String id_wechat;
        private String phone;
        private String tag;

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

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }
    }

    public static class ExtBean {
        /**
         * id : 5
         * action_name :
         * action_type :
         * action_time : null
         * action_address :
         * action_position :
         * action_remark :
         * action_images : ,
         */

        private String id;
        private String action_name;
        private String action_type;
        private String action_time;
        private String action_address;
        private String action_position;
        private String action_remark;
        private String action_images;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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
    }
}
