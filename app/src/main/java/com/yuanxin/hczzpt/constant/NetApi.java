package com.yuanxin.hczzpt.constant;

import com.qflbai.lib.net.url.BaseNetApi;
import com.qflbai.lib.net.url.NetBaseUrl;

/**
 * @author: qflbai
 * @CreateDate: 2020/1/16 14:44
 * @Version: 1.0
 * @description:
 */
public class NetApi extends BaseNetApi {

    public static String host = "211.149.134.103";
    public static String baseUrl = "http://" + host + ":86";

    public static void setBaseUrl() {
        NetBaseUrl.setBaseURL(baseUrl);
    }

    public interface Path {
        /**
         * 登录
         */
        String login = "login";

        /**
         * 退出登录
         */
        String loginout = "loginout";

        /**
         * 嫌疑人列表
         */
        String manlist = "manlist";
        /**
         * 嫌疑人详情
         */
        String maninfo = "maninfo";

        /**
         * 案件信息
         */
        String caseInfo = "case";

        /**
         * 案件性质
         */
        String caseinfo = "caseinfo";

        /**
         * 协作提交
         */
        String caseadd = "caseadd";

        /**
         * 协作添加图片
         */
        String casepic = "casepic";

        /**
         * 嫌疑人添加
         */
        String distrustexadd = "distrustexadd";

        /**
         * 头像上传
         */
        String distrustpic = "distrustexupic";

        /**
         * 嫌疑人案件图片(删除也用)
         */
        String distrustexpic = "distrustexupic";

        /**
         * 获取案件信息
         */
        String casedetail = "casedetail";

        /**
         * 结案
         */
        String overend = "overend";

        /**
         * 获取协作部门和人员
         */
        String caseman = "caseman";

        /**
         * 添加协作
         */
        String casehadd =  "casehada";

        /**
         * 研制信息图片上传
         */
        String casempic = "casempic";

        /**
         * 研制信息提交
         */
        String casemadd = "casemadd";
        /**
         * 删除嫌疑人
         */
        String distrustdel = "distrustdel";

        /**
         *编辑嫌疑人
         */
        String distrustexedit = "distrustexedit";

        /**
         * 涉毒人员详情
         */
        String distrustinfo = "distrustinfo";
    }
}
