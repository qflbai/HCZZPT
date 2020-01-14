package com.yuanxin.hczzpt.platforms.ui.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.qflbai.lib.base.activity.BaseActivity;
import com.yuanxin.hczzpt.R;
import com.yuanxin.hczzpt.event.EventAction;
import com.yuanxin.hczzpt.event.EventMessage;
import com.yuanxin.hczzpt.image.GlideEngine;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AddXyrActivity extends BaseActivity {

    @BindView(R.id.iv_tx)
    ImageView ivTx;
    private int action;
    private String title;
    private List<LocalMedia> selectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_add_xyr);

        if (title != null) {
            initBackToolbar(title);
        } else {
            initBackToolbar("添加嫌疑人");
        }
        getIvBack().setImageResource(R.mipmap.ic_bs_fh);

    }

    @Subscribe(sticky = true)
    public void getEventMessage(EventMessage eventMessage) {
        action = eventMessage.getAction();
        title = eventMessage.getContent();

    }

    @OnClick(R.id.iv_tx)
    public void chooseImage() {
        PictureSelector.create(AddXyrActivity.this)
                .themeStyle(R.style.picture_default_style) // xml设置主题
                //.setPictureStyle(mPictureParameterStyle)// 动态自定义相册主题
                //.setPictureWindowAnimationStyle(animationStyle)// 自定义页面启动动画
                .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)// 设置相册Activity方向，不设置默认使用系统
                .isNotPreviewDownload(false)// 预览图片长按是否可以下载
                .loadImageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                .selectionMode(PictureConfig.SINGLE)
                .enableCrop(true)// 是否裁剪
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                .isDragFrame(true)
                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                .forResult(PictureConfig.CHOOSE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回五种path
                    // 1.media.getPath(); 原图path
                    // 2.media.getCutPath();裁剪后path，需判断media.isCut();切勿直接使用
                    // 3.media.getCompressPath();压缩后path，需判断media.isCompressed();切勿直接使用
                    // 4.media.getOriginalPath()); media.isOriginal());为true时此字段才有值
                    // 5.media.getAndroidQToPath();Android Q版本特有返回的字段，但如果开启了压缩或裁剪还是取裁剪或压缩路径；注意：.isAndroidQTransform 为false 此字段将返回空
                    // 如果同时开启裁剪和压缩，则取压缩路径为准因为是先裁剪后压缩
                    for (LocalMedia media : selectList) {
                        String cutPath = media.getCutPath();

                        Glide.with(mContext)
                                .load(cutPath)
                                .into(ivTx);
                    }
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        EventBus.getDefault().removeAllStickyEvents();
    }
}
