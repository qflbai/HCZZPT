package com.yuanxin.hczzpt.platforms.bean;

import java.util.List;

/**
 * @author: qflbai
 * @CreateDate: 2020/1/9 16:10
 * @Version: 1.0
 * @description:
 */
public class TeamDetailsInfo {

    /**
     * resultCode : 1
     * data : {"mlist":[{"id":"10","man_name":"李白","id_card":"440103199104180016","id_car":"粤abbb","id_wechat":"好好","phone":"13005333110","action_address":"哈哈哈","action_remark":"几节课","avatar_id":"/Uploads/Images/2020-03-15/IMG_20200315_14443910_31955891.jpeg"}],"hlist":[{"id":"113","user_id":"46","user_role":"28","type":"0","summary":"213123123","images":"","files":"","created_time":"2020-02-20 11:47:16"}],"user_id":"1","_case":{"id":"42","case_images":"/Uploads/Images/2020-03-15/IMG_20200315_14423916_73080917.jpeg,/Uploads/Images/2020-03-15/IMG_20200315_14425094_72748709.jpeg","case_name":"QQ飞车","case_type":"抢劫","happened_time":"2020-03-15 14:09:42","reported_time":"2020-03-15 14:07:42","case_state":"20","created_man":"超级管理员","created_role":"","case_summary":"","action":"0"}}
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
         * mlist : [{"id":"10","man_name":"李白","id_card":"440103199104180016","id_car":"粤abbb","id_wechat":"好好","phone":"13005333110","action_address":"哈哈哈","action_remark":"几节课","avatar_id":"/Uploads/Images/2020-03-15/IMG_20200315_14443910_31955891.jpeg"}]
         * hlist : [{"id":"113","user_id":"46","user_role":"28","type":"0","summary":"213123123","images":"","files":"","created_time":"2020-02-20 11:47:16"}]
         * user_id : 1
         * _case : {"id":"42","case_images":"/Uploads/Images/2020-03-15/IMG_20200315_14423916_73080917.jpeg,/Uploads/Images/2020-03-15/IMG_20200315_14425094_72748709.jpeg","case_name":"QQ飞车","case_type":"抢劫","happened_time":"2020-03-15 14:09:42","reported_time":"2020-03-15 14:07:42","case_state":"20","created_man":"超级管理员","created_role":"","case_summary":"","action":"0"}
         */

        private String user_id;
        private CaseBean _case;
        private List<MlistBean> mlist;
        private List<HlistBean> hlist;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public CaseBean get_case() {
            return _case;
        }

        public void set_case(CaseBean _case) {
            this._case = _case;
        }

        public List<MlistBean> getMlist() {
            return mlist;
        }

        public void setMlist(List<MlistBean> mlist) {
            this.mlist = mlist;
        }

        public List<HlistBean> getHlist() {
            return hlist;
        }

        public void setHlist(List<HlistBean> hlist) {
            this.hlist = hlist;
        }

        public static class CaseBean {
            /**
             * id : 42
             * case_images : /Uploads/Images/2020-03-15/IMG_20200315_14423916_73080917.jpeg,/Uploads/Images/2020-03-15/IMG_20200315_14425094_72748709.jpeg
             * case_name : QQ飞车
             * case_type : 抢劫
             * happened_time : 2020-03-15 14:09:42
             * reported_time : 2020-03-15 14:07:42
             * case_state : 20
             * created_man : 超级管理员
             * created_role :
             * case_summary :
             * action : 0
             */

            private String id;
            private String case_images;
            private String case_name;
            private String case_type;
            private String happened_time;
            private String reported_time;
            private String case_state;
            private String created_man;
            private String created_role;
            private String case_summary;
            private String action;
            private String system_id;

            public String getSystem_id() {
                return system_id;
            }

            public void setSystem_id(String system_id) {
                this.system_id = system_id;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCase_images() {
                return case_images;
            }

            public void setCase_images(String case_images) {
                this.case_images = case_images;
            }

            public String getCase_name() {
                return case_name;
            }

            public void setCase_name(String case_name) {
                this.case_name = case_name;
            }

            public String getCase_type() {
                return case_type;
            }

            public void setCase_type(String case_type) {
                this.case_type = case_type;
            }

            public String getHappened_time() {
                return happened_time;
            }

            public void setHappened_time(String happened_time) {
                this.happened_time = happened_time;
            }

            public String getReported_time() {
                return reported_time;
            }

            public void setReported_time(String reported_time) {
                this.reported_time = reported_time;
            }

            public String getCase_state() {
                return case_state;
            }

            public void setCase_state(String case_state) {
                this.case_state = case_state;
            }

            public String getCreated_man() {
                return created_man;
            }

            public void setCreated_man(String created_man) {
                this.created_man = created_man;
            }

            public String getCreated_role() {
                return created_role;
            }

            public void setCreated_role(String created_role) {
                this.created_role = created_role;
            }

            public String getCase_summary() {
                return case_summary;
            }

            public void setCase_summary(String case_summary) {
                this.case_summary = case_summary;
            }

            public String getAction() {
                return action;
            }

            public void setAction(String action) {
                this.action = action;
            }
        }

        public static class MlistBean {
            /**
             * id : 10
             * man_name : 李白
             * id_card : 440103199104180016
             * id_car : 粤abbb
             * id_wechat : 好好
             * phone : 13005333110
             * action_address : 哈哈哈
             * action_remark : 几节课
             * avatar_id : /Uploads/Images/2020-03-15/IMG_20200315_14443910_31955891.jpeg
             */

            private String id;
            private String man_name;
            private String id_card;
            private String id_car;
            private String id_wechat;
            private String phone;
            private String action_address;
            private String action_remark;
            private String avatar_id;
            private String tag;

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMan_name() {
                return man_name;
            }

            public void setMan_name(String man_name) {
                this.man_name = man_name;
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

            public String getAction_address() {
                return action_address;
            }

            public void setAction_address(String action_address) {
                this.action_address = action_address;
            }

            public String getAction_remark() {
                return action_remark;
            }

            public void setAction_remark(String action_remark) {
                this.action_remark = action_remark;
            }

            public String getAvatar_id() {
                return avatar_id;
            }

            public void setAvatar_id(String avatar_id) {
                this.avatar_id = avatar_id;
            }
        }

        public static class HlistBean {

            /**
             * id : 30
             * user_id : 1
             * user_role : 0
             * type : 0
             * summary : 原因
             * images :
             * files : [{"id":"45","name":"sound_4.wav","path":"/Uploads/CFiles/2020-03-18/sound_4_66072699.wav"},{"id":"46","name":"sound_3.wav","path":"/Uploads/CFiles/2020-03-18/sound_3_76046923.wav"}]
             * created_time : 2020-03-18 03:19:20
             * system_id : 9999999
             * system_role :
             * avatar_id : /Uploads/Images/2020-03-09/WechatIMG7024_52907573.png
             */

            private String id;
            private String user_id;
            private String user_role;
            private String type;
            private String summary;
            private String images;
            private String created_time;
            private String system_id;
            private String system_role;
            private String avatar_id;
            private List<FilesBean> files;

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

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public String getImages() {
                return images;
            }

            public void setImages(String images) {
                this.images = images;
            }

            public String getCreated_time() {
                return created_time;
            }

            public void setCreated_time(String created_time) {
                this.created_time = created_time;
            }

            public String getSystem_id() {
                return system_id;
            }

            public void setSystem_id(String system_id) {
                this.system_id = system_id;
            }

            public String getSystem_role() {
                return system_role;
            }

            public void setSystem_role(String system_role) {
                this.system_role = system_role;
            }

            public String getAvatar_id() {
                return avatar_id;
            }

            public void setAvatar_id(String avatar_id) {
                this.avatar_id = avatar_id;
            }

            public List<FilesBean> getFiles() {
                return files;
            }

            public void setFiles(List<FilesBean> files) {
                this.files = files;
            }

            public static class FilesBean {
                /**
                 * id : 45
                 * name : sound_4.wav
                 * path : /Uploads/CFiles/2020-03-18/sound_4_66072699.wav
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
    }
}
