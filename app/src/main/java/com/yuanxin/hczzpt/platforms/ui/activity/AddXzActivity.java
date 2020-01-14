package com.yuanxin.hczzpt.platforms.ui.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.qflbai.lib.base.activity.BaseActivity;
import com.qflbai.lib.ui.widget.image.ImageBox;
import com.yuanxin.hczzpt.R;
import com.yuanxin.hczzpt.image.GlideEngine;
import com.yuanxin.hczzpt.time.TimePickerUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AddXzActivity extends BaseActivity {
    @BindView(R.id.ll_add_xyr)
    LinearLayout mLLAddXyr;
    @BindView(R.id.ib)
    ImageBox mIb;
    @BindView(R.id.tv_ba_time)
    TextView tvBaTime;
    @BindView(R.id.tv_af_time)
    TextView tvAfTime;
    private String afTime;
    private String baTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_xz);

        initBackToolbar("案件详情");
        getIvBack().setImageResource(R.mipmap.ic_bs_fh);

        initIb();
    }

    private void initIb() {
        mIb.setOnlineImageLoader(new ImageBox.OnlineImageLoader() {
            @Override
            public void onLoadImage(ImageView iv, String url) {
                Glide.with(mContext).load(url).into(iv);
            }
        });
        mIb.setOnImageClickListener(new ImageBox.OnImageClickListener() {
            @Override
            public void onImageClick(int position, String filePath, String tag, int type, ImageView iv) {

            }

            @Override
            public void onDeleteClick(int position, String filePath, String tag, int type) {
                //移除position位置的图片
                mIb.removeImage(position);
            }

            @Override
            public void onAddClick() {
                chooseImage();

            }
        });
    }

    public void chooseImage() {
        PictureSelector.create(AddXzActivity.this)
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
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回五种path
                    // 1.media.getPath(); 原图path
                    // 2.media.getCutPath();裁剪后path，需判断media.isCut();切勿直接使用
                    // 3.media.getCompressPath();压缩后path，需判断media.isCompressed();切勿直接使用
                    // 4.media.getOriginalPath()); media.isOriginal());为true时此字段才有值
                    // 5.media.getAndroidQToPath();Android Q版本特有返回的字段，但如果开启了压缩或裁剪还是取裁剪或压缩路径；注意：.isAndroidQTransform 为false 此字段将返回空
                    // 如果同时开启裁剪和压缩，则取压缩路径为准因为是先裁剪后压缩
                    for (LocalMedia media : selectList) {
                        String cutPath = media.getCutPath();
                        mIb.addImage(cutPath);
                    }
                    break;
            }
        }
    }

    @OnClick(R.id.tv_add_xyr)
    public void addXyr() {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_recycler_platforms_team_xyr_info, null);

        mLLAddXyr.addView(inflate);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) inflate.getLayoutParams();
        layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
        layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;

    }

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
    public String mTime;
    @OnClick({R.id.iv_ba_time,R.id.iv_af_time})
    public void time(View view){
        TimePickerBuilder alphaGradient = new TimePickerBuilder(mContext, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                String time = simpleDateFormat.format(date);
                if(view.getId()==R.id.iv_ba_time){

                    baTime = time;
                    tvBaTime.setText(time);
                }else {
                    tvAfTime.setText(time);
                    afTime = time;
                }
            }
        });


        //时间选择器
        TimePickerView pvTime = TimePickerUtil.getYMDHMSTimeTimePickerView(alphaGradient);
        pvTime.show();
    }
}
