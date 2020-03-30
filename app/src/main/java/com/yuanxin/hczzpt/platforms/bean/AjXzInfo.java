package com.yuanxin.hczzpt.platforms.bean;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

public class AjXzInfo {

    /**
     * info :
     * list : [{"id":"类型ID","name":"类型名称"}]
     * ulist :
     */

    private String info;
    private String ulist;
    private List<ListBean> list;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getUlist() {
        return ulist;
    }

    public void setUlist(String ulist) {
        this.ulist = ulist;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean implements IPickerViewData {
        /**
         * id : 类型ID
         * name : 类型名称
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

        @Override
        public String getPickerViewText() {
            return name;
        }
    }
}
