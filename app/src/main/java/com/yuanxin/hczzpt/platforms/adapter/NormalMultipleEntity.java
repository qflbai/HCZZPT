package com.yuanxin.hczzpt.platforms.adapter;


public class NormalMultipleEntity {

    //标题
    public static final int TITLE = 1;
    //案件详情列表
    public static final int LAW_CASE_DETAILS_ITEM = 2;
    //创建人信息
    public static final int CJR_INFO = 3;
    //案件信息
    public static final int LAW_CASE_INFO = 4;
    //嫌疑人信息
    public static final int XYR_INFO = 5;
    //研制信息
    public static final int YZ_INFO = 6;
    //标题附件
    public static final int TITLE_FJ = 7;
    //标题时间
    public static final int TITLE_FJ_TIME = 8;
    public static final int TITLE_FJ_IMG = 9;
    public int type;
    public Object content;

    public NormalMultipleEntity(int type) {
        this.type = type;
    }

    public NormalMultipleEntity(int type, Object content) {
        this.type = type;
        this.content = content;
    }
}
