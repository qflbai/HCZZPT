package com.yuanxin.hczzpt.platforms.ui.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.qflbai.lib.base.activity.BaseActivity;
import com.qflbai.lib.net.RetrofitManage;
import com.qflbai.lib.net.body.ServerResponseResult;
import com.qflbai.lib.net.callback.NetCallback;
import com.qflbai.lib.net.rxjava.NetObserver;
import com.qflbai.lib.ui.widget.image.ImageBox;
import com.qflbai.lib.utils.toast.ToastUtil;
import com.yuanxin.hczzpt.R;
import com.yuanxin.hczzpt.common.utils.CommonUtils;
import com.yuanxin.hczzpt.common.utils.RetrofitApi;
import com.yuanxin.hczzpt.constant.NetApi;
import com.yuanxin.hczzpt.event.EventAction;
import com.yuanxin.hczzpt.event.EventMessage;
import com.yuanxin.hczzpt.image.GlideEngine;
import com.yuanxin.hczzpt.platforms.bean.AddXyrInfo;
import com.yuanxin.hczzpt.platforms.bean.AjXzInfo;
import com.yuanxin.hczzpt.platforms.bean.XzInfo;
import com.yuanxin.hczzpt.time.SelectPickerUtil;
import com.yuanxin.hczzpt.time.TimePickerUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AddXyrActivity extends BaseActivity {

    @BindView(R.id.iv_tx)
    ImageView ivTx;
    @BindView(R.id.ib)
    ImageBox mIb;
    @BindView(R.id.tv_xyr_number)
    TextView mTvXyrNumber;
    @BindView(R.id.et_xyr_name)
    EditText mEtXyrName;
    @BindView(R.id.et_wxh)
    EditText mEtWxh;
    @BindView(R.id.et_sfzh)
    EditText mEtSfzh;
    @BindView(R.id.et_cphm)
    EditText mEtCphm;
    @BindView(R.id.et_sjhm)
    EditText mEtSjhm;
    @BindView(R.id.et_zpmc)
    EditText mEtZpmc;
    @BindView(R.id.tv_zpsj)
    TextView mTvZpsj;
    @BindView(R.id.tv_ajxz)
    TextView mTvAjxz;
    @BindView(R.id.et_zb_dz)
    EditText mEtZbdz;
    @BindView(R.id.et_bz)
    EditText mEtbz;
    @BindView(R.id.tv_submit)
    TextView mTvSubmit;
    private int action;
    private String title;
    private List<LocalMedia> selectList;
    private int actionClick;
    private XzInfo mXzInfo;
    private String mZpsj;
    private ArrayList<AjXzInfo.ListBean> mAjXzInfos;
    private AjXzInfo.ListBean mAjXzInfo;
    private List<XzImageInfo> xzImageInfos;
    private AddXyrInfo addXyrInfo;

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

        ivTx.setVisibility(View.GONE);

        getIvBack().setImageResource(R.mipmap.ic_bs_fh);
        initIb();
        getCaseInfo();
    }

    @Subscribe(sticky = true)
    public void getXzInfo(XzInfo xzInfo) {
        mXzInfo = xzInfo;
    }

    private void initIb() {
        xzImageInfos = new ArrayList<>();
        mIb.setVisibility(View.GONE);
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
                deletImage(position,filePath);
            }

            @Override
            public void onAddClick() {
                actionClick = 1;
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
        });
    }


    @Subscribe(sticky = true)
    public void getEventMessage(EventMessage eventMessage) {
        action = eventMessage.getAction();
        title = eventMessage.getContent();

    }

    @OnClick(R.id.iv_tx)
    public void chooseImage() {
        actionClick = 0;
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
                        if (actionClick == 0) {

                            upTx(cutPath);
                        } else {
                            addImage(cutPath);
                        }
                    }
                    break;
            }
        }
    }

    /**
     * 上传头像
     */
    private void upTx(String cutPath) {
        String path = NetApi.Path.distrustpic;
        Map<String, RequestBody> map = new HashMap<>();

        RequestBody b1 = RequestBody.create(MediaType.parse("multipart/form-data"), addXyrInfo != null ? addXyrInfo.getId() : "");
        RequestBody b3 = RequestBody.create(MediaType.parse("multipart/form-data"), "android");

        map.put("user_id", b1);
        map.put("version", b3);

        File file = new File(cutPath);
        RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        String name = file.getName();

        MultipartBody.Part part = MultipartBody.Part.createFormData("file", name, imageBody);
        // map.put("file", name);

        showDialogLoading();
        RetrofitManage.newInstance().createService(RetrofitApi.class)
                .uplodaOne(path, map, part)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetObserver(new NetCallback() {
                    @Override
                    public void onResponse(String s) {
                        hideDialogLoading();

                        ServerResponseResult serverResponseResult = JSON.parseObject(s, ServerResponseResult.class);
                        String code = serverResponseResult.getCode();
                        if ("200".equals(code)) {
                            Object data = serverResponseResult.getData();
                            String s1 = JSON.toJSONString(data);

                            XzImageInfo xzImageInfo = JSON.parseObject(s1, XzImageInfo.class);
                            xzImageInfos.add(xzImageInfo);

                            Glide.with(mContext)
                                    .load(NetApi.baseUrl + xzImageInfo.getFile())
                                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                    .into(ivTx);
                        }
                    }

                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        hideDialogLoading();
                    }
                }));
    }

    private void addImage(String cutPath) {

        String path = NetApi.Path.distrustexpic;
        Map<String, RequestBody> map = new HashMap<>();

        RequestBody b1 = RequestBody.create(MediaType.parse("multipart/form-data"), addXyrInfo != null ? addXyrInfo.getId() : "");
        RequestBody b2 = RequestBody.create(MediaType.parse("multipart/form-data"), "add");
        RequestBody b3 = RequestBody.create(MediaType.parse("multipart/form-data"), "android");

        map.put("id", b1);
        map.put("action", b2);
        map.put("version", b3);

        File file = new File(cutPath);
        RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        String name = file.getName();

        MultipartBody.Part part = MultipartBody.Part.createFormData("file", name, imageBody);
        // map.put("file", name);

        showDialogLoading();
        RetrofitManage.newInstance().createService(RetrofitApi.class)
                .uplodaOne(path, map, part)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetObserver(new NetCallback() {
                    @Override
                    public void onResponse(String s) {
                        hideDialogLoading();

                        ServerResponseResult serverResponseResult = JSON.parseObject(s, ServerResponseResult.class);
                        String code = serverResponseResult.getCode();
                        if ("200".equals(code)) {
                            Object data = serverResponseResult.getData();
                            String s1 = JSON.toJSONString(data);

                            XzImageInfo xzImageInfo = JSON.parseObject(s1, XzImageInfo.class);
                            String url = NetApi.baseUrl + xzImageInfo.getFile();
                            xzImageInfo.setFile(url);
                            xzImageInfos.add(xzImageInfo);
                            mIb.addImage(url);
                        }
                    }

                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        hideDialogLoading();
                    }
                }));
    }


    private void getCaseInfo() {
        mAjXzInfos = new ArrayList<>();
        String path = NetApi.Path.caseinfo;
        Map<String, Object> map = new HashMap<>();
        map.put("version", "android");
        showDialogLoading();

        RetrofitManage.newInstance().createService()
                .getNet(path, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetObserver(new NetCallback() {
                    @Override
                    public void onResponse(String s) {
                        hideDialogLoading();

                        ServerResponseResult serverResponseResult = JSON.parseObject(s, ServerResponseResult.class);
                        String code = serverResponseResult.getCode();
                        if ("200".equals(code)) {
                            Object data = serverResponseResult.getData();
                            String s1 = JSON.toJSONString(data);
                            AjXzInfo ajXzInfo = JSON.parseObject(s1, AjXzInfo.class);
                            List<AjXzInfo.ListBean> list = ajXzInfo.getList();
                            mAjXzInfos.addAll(list);

                        }

                    }

                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                        hideDialogLoading();


                    }
                }));
    }

    @OnClick(R.id.iv_aj_xz)
    public void anXzXz() {
        OptionsPickerBuilder optionsPickerBuilder = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int i, int i1, int i2, View view) {
                mAjXzInfo = mAjXzInfos.get(i);
                mTvAjxz.setText(mAjXzInfo.getName());
            }
        });

        OptionsPickerBuilder selectPickerBuilder = SelectPickerUtil.getSelectPickerBuilder(optionsPickerBuilder);
        OptionsPickerView<AjXzInfo.ListBean> build = selectPickerBuilder.build();
        // build.setSelectOptions(1);
        build.setPicker(mAjXzInfos);

        build.show();
    }

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
    public String mTime;

    @OnClick(R.id.iv_time)
    public void time(View view) {
        TimePickerBuilder alphaGradient = new TimePickerBuilder(mContext, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                String time = simpleDateFormat.format(date);

                mZpsj = time;
                mTvZpsj.setText(time);

            }
        });


        //时间选择器
        TimePickerView pvTime = TimePickerUtil.getYMDHMSTimeTimePickerView(alphaGradient);
        pvTime.show();
    }

    @OnClick(R.id.tv_submit)
    public void submit() {

        String path = NetApi.Path.distrustexadd;
        Map<String, Object> map = new HashMap<>();

        String sfzh = mEtSfzh.getText().toString();
        if (TextUtils.isEmpty(sfzh)) {
            ToastUtil.show(mContext, "请填写身份证号");
            return;
        }

        if (TextUtils.isEmpty(sfzh)) {
            ToastUtil.show(mContext, "请填写姓名");
            return;
        }

        map.put("case_id", mXzInfo != null ? mXzInfo.getId() : null);

        map.put("man_name", mEtXyrName.getText().toString());
        map.put("id_wechat", mEtWxh.getText().toString());
        map.put("id_card", sfzh);
        map.put("id_car", mEtCphm.getText().toString());
        map.put("phone", mEtSjhm.getText().toString());
        map.put("action_name", mEtZpmc.getText().toString());

        map.put("action_type", mAjXzInfo != null ? mAjXzInfo.getId() : null);
        map.put("action_time", mZpsj);
        map.put("action_address", mEtZbdz.getText().toString());
        map.put("action_remark", mEtbz.getText().toString());

        map.put("version", "android");

        CommonUtils.removeNull(map);

        showDialogLoading();
        RetrofitManage.newInstance().createService()
                .postFormNet(path, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetObserver(new NetCallback() {
                    @Override
                    public void onResponse(String s) {

                        hideDialogLoading();

                        ServerResponseResult serverResponseResult = JSON.parseObject(s, ServerResponseResult.class);
                        String code = serverResponseResult.getCode();
                        if ("200".equals(code)) {
                            Object data = serverResponseResult.getData();
                            String s1 = JSON.toJSONString(data);
                            addXyrInfo = JSON.parseObject(s1, AddXyrInfo.class);
                            mTvSubmit.setVisibility(View.GONE);
                            ivTx.setVisibility(View.VISIBLE);
                            mIb.setVisibility(View.VISIBLE);
                            getTvSubtitleTitle().setVisibility(View.GONE);
                        }

                    }

                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                        hideDialogLoading();


                    }
                }));
    }

    private void deletImage(int position,String filePath){
        String path = NetApi.Path.distrustexpic;
        Map<String, Object> map = new HashMap<>();

        map.put("id", addXyrInfo != null ? addXyrInfo.getId() : null);
        map.put("del_id", mEtXyrName.getText().toString());
        map.put("action", "del");
        map.put("version", "android");


        CommonUtils.removeNull(map);

        showDialogLoading();
        RetrofitManage.newInstance().createService()
                .postFormNet(path, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetObserver(new NetCallback() {
                    @Override
                    public void onResponse(String s) {

                        hideDialogLoading();

                        ServerResponseResult serverResponseResult = JSON.parseObject(s, ServerResponseResult.class);
                        String code = serverResponseResult.getCode();
                        if ("200".equals(code)) {
                            XzImageInfo deleteXzImageInfo = null;
                            for(XzImageInfo xzImageInfo:xzImageInfos){
                                String file = xzImageInfo.getFile();
                                if(filePath.equals(file)){
                                    deleteXzImageInfo = xzImageInfo;
                                }
                            }
                            if(deleteXzImageInfo!=null){
                                xzImageInfos.remove(deleteXzImageInfo);
                            }
                            //移除position位置的图片
                            mIb.removeImage(position);
                        }

                    }

                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                        hideDialogLoading();


                    }
                }));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        EventBus.getDefault().removeAllStickyEvents();
    }

    @Override
    protected void onFinish() {
        if(addXyrInfo!=null){
            EventBus.getDefault().post(new EventMessage(1));
        }
        finish();
    }

    @Override
    public void onBackPressed() {
        if(addXyrInfo!=null){
            EventBus.getDefault().post(new EventMessage(1));
        }
        finish();
    }
}
