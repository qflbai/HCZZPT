package com.yuanxin.hczzpt;

import android.content.Context;

import com.luck.picture.lib.app.PictureAppMaster;
import com.luck.picture.lib.crash.PictureSelectorCrashUtils;
import com.qflbai.lib.base.BaseApplication;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshFooter;
import com.scwang.smart.refresh.layout.api.RefreshHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.DefaultRefreshFooterCreator;
import com.scwang.smart.refresh.layout.listener.DefaultRefreshHeaderCreator;
import com.yuanxin.hczzpt.constant.NetApi;

/**
 * @author: qflbai
 * @CreateDate: 2020/1/8 16:10
 * @Version: 1.0
 * @description:
 */
public class App extends BaseApplication {
    public static String userName;
    @Override
    public void onCreate() {
        super.onCreate();
        NetApi.setBaseUrl();
        initSmartRefreshLayout();
    }

    private void  initSmartRefreshLayout(){
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setEnableRefresh(false);
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });



        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }
}
