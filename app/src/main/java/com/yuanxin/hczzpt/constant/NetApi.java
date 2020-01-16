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
    public static String host = "192.168.11.75";
    public static String baseUrl = "http://" + host + ":8080";

    public static void setBaseUrl() {
        NetBaseUrl.setBaseURL(baseUrl);
    }

    public interface Path {

    }
}
