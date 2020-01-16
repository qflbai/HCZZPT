package com.yuanxin.hczzpt;

import com.luck.picture.lib.app.PictureAppMaster;
import com.luck.picture.lib.crash.PictureSelectorCrashUtils;
import com.qflbai.lib.base.BaseApplication;
import com.yuanxin.hczzpt.constant.NetApi;

/**
 * @author: qflbai
 * @CreateDate: 2020/1/8 16:10
 * @Version: 1.0
 * @description:
 */
public class App extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        NetApi.setBaseUrl();
    }
}
