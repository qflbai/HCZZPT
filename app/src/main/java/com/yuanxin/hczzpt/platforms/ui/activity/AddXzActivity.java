package com.yuanxin.hczzpt.platforms.ui.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.qflbai.lib.base.activity.BaseActivity;
import com.qflbai.lib.net.RetrofitManage;
import com.qflbai.lib.net.body.ServerResponseResult;
import com.qflbai.lib.net.callback.NetCallback;
import com.qflbai.lib.net.rxjava.NetObserver;
import com.qflbai.lib.ui.widget.image.ImageBox;
import com.qflbai.lib.utils.StringUtils;
import com.qflbai.lib.utils.toast.ToastUtil;
import com.yuanxin.hczzpt.R;
import com.yuanxin.hczzpt.common.utils.CommonUtils;
import com.yuanxin.hczzpt.common.utils.RetrofitApi;
import com.yuanxin.hczzpt.constant.NetApi;
import com.yuanxin.hczzpt.event.EventMessage;
import com.yuanxin.hczzpt.home.bean.AjInfo;
import com.yuanxin.hczzpt.image.GlideEngine;
import com.yuanxin.hczzpt.platforms.bean.AjXzInfo;
import com.yuanxin.hczzpt.platforms.bean.TeamDetailsInfo;
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

public class AddXzActivity extends BaseActivity {
    @BindView(R.id.ll_add_xyr)
    LinearLayout mLLAddXyr;
    @BindView(R.id.ib)
    ImageBox mIb;
    @BindView(R.id.tv_ba_time)
    TextView tvBaTime;
    @BindView(R.id.tv_af_time)
    TextView tvAfTime;
    @BindView(R.id.rl_5)
    RelativeLayout rl5;
    @BindView(R.id.ll_1)
    LinearLayout ll1;
    @BindView(R.id.et_aj_name)
    EditText mEtAjName;
    @BindView(R.id.tv_aj_xz)
    TextView mTvAjXz;
    @BindView(R.id.et_ajnx)
    EditText mEtAjnx;

    private String afTime;
    private String baTime;
    private List<AjXzInfo.ListBean> mAjXzInfos;
    private AjXzInfo.ListBean mAjXzInfo;
    private XzInfo xzInfo;
    private List<XzImageInfo> xzImageInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_xz);
        EventBus.getDefault().register(this);
        initBackToolbar("案件详情");
        getIvBack().setImageResource(R.mipmap.ic_bs_fh);

        initIb();
        TextView tvSubtitleTitle = getTvSubtitleTitle();
        tvSubtitleTitle.setVisibility(View.VISIBLE);
        tvSubtitleTitle.setText("提交");
        tvSubtitleTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ajSubmint();
            }
        });
        tvSubtitleTitle.setTextColor(getResources().getColor(R.color.white));
        rl5.setVisibility(View.GONE);
        ll1.setVisibility(View.GONE);
        mIb.setVisibility(View.GONE);
        getCaseInfo();
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

    private void initIb() {
        xzImageInfos = new ArrayList<>();
        mIb.setDeletable(false);
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
                // mIb.removeImage(position);
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

    @Subscribe
    public void getEventMessage(EventMessage eventMessage) {
        int action = eventMessage.getAction();
        if (action == 1) {
            sxAjxx();
        }
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
                        addImage(cutPath);
                    }
                    break;
            }
        }
    }

    @OnClick(R.id.tv_ja)
    public void ja() {
        String path = NetApi.Path.overend;
        Map<String, Object> map = new HashMap<>();
        map.put("id", mAjXzInfo.getId());
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
                            ToastUtil.showCenter(mContext, "已结案");
                            finish();

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

    @OnClick(R.id.tv_yqxtza)
    public void yqxtzn() {
        Intent intent = new Intent(mContext, XzSelectActivity.class);
        intent.putExtra("case_id", xzInfo.getId());
        startActivity(intent);
    }

    @OnClick(R.id.tv_add_xyr)
    public void addXyr() {

        Intent intent = new Intent(mContext, AddXyrActivity.class);
        EventBus.getDefault().postSticky(xzInfo);
        startActivity(intent);


    }

    @OnClick(R.id.ll_ajxz)
    public void anXzXz() {
        OptionsPickerBuilder optionsPickerBuilder = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int i, int i1, int i2, View view) {
                mAjXzInfo = mAjXzInfos.get(i);
                mTvAjXz.setText(mAjXzInfo.getName());
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

    @OnClick({R.id.ll_basj, R.id.ll_afsj})
    public void time(View view) {
        TimePickerBuilder alphaGradient = new TimePickerBuilder(mContext, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                String time = simpleDateFormat.format(date);
                if (view.getId() == R.id.ll_basj) {

                    baTime = time;
                    tvBaTime.setText(time);
                } else {
                    tvAfTime.setText(time);
                    afTime = time;
                }
            }
        });


        //时间选择器
        TimePickerView pvTime = TimePickerUtil.getYMDHMSTimeTimePickerView(alphaGradient);
        pvTime.show();
    }

    private void sxAjxx() {
        String path = NetApi.Path.casedetail;
        Map<String, Object> map = new HashMap<>();
        map.put("id", xzInfo.getId());
        map.put("version", "android");

        CommonUtils.removeNull(map);

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
                            TeamDetailsInfo teamDetailsInfo = JSON.parseObject(s1, TeamDetailsInfo.class);
                            TeamDetailsInfo.DataBean data1 = teamDetailsInfo.getData();


                            List<TeamDetailsInfo.DataBean.MlistBean> mlist = data1.getMlist();
                            mLLAddXyr.removeAllViews();
                            for (TeamDetailsInfo.DataBean.MlistBean mlistBean : mlist) {
                                getXyrShow(mlistBean);
                            }

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

    private void getXyrShow(TeamDetailsInfo.DataBean.MlistBean data) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_recycler_platforms_team_xyr_info1, null);
        TextView tvDelete = inflate.findViewById(R.id.tv_delete);

        TextView tvName = inflate.findViewById(R.id.tv_name);
        TextView tvWxh = inflate.findViewById(R.id.tv_wxh);
        TextView tvCardid = inflate.findViewById(R.id.tv_cardid);
        TextView tvXyrid = inflate.findViewById(R.id.criminal_id);
        TextView tvFl = inflate.findViewById(R.id.tv_fl);


        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(data.getId());

            }
        });
        mLLAddXyr.addView(inflate);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) inflate.getLayoutParams();
        layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
        layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;

        String tag = data.getTag();
        if ("wen".equals(this.tag)) {
            this.tag = "维稳人员";
        } else if ("du".equals(this.tag)) {
            this.tag = "涉毒人员";
        } else if ("dui".equals(this.tag)) {
            this.tag = "对象人员";
        } else if ("man".equals(this.tag)) {
            this.tag = "嫌疑人";
        } else if ("otd".equals(this.tag)) {
            this.tag = "其他";
        }
        tvFl.setText(tag);
        tvName.setText(data.getMan_name());
        tvCardid.setText(data.getId_card());
        tvWxh.setText(data.getId_wechat());
        tvXyrid.setText(data.getId());
    }

    private void delete(String id){

        String path = NetApi.Path.distrustdel;
        Map<String, Object> map = new HashMap<>();
        map.put("version", "android");
        map.put("id", id);
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
                           sxAjxx();
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


    private void ajSubmint() {
        String ajNmae = mEtAjName.getText().toString();
        String ajnx = mEtAjnx.getText().toString();
        if (TextUtils.isEmpty(ajNmae)) {
            ToastUtil.show(mContext, "请输入案件名称");
            return;
        }

        if (mAjXzInfo == null) {
            ToastUtil.show(mContext, "请选择案件性质");
            return;
        }

        String name = mAjXzInfo.getName();

        String path = NetApi.Path.caseadd;
        Map<String, Object> map = new HashMap<>();
        map.put("case_name", ajNmae);
        map.put("happened_time", afTime);
        map.put("reported_time", baTime);
        map.put("case_type", mAjXzInfo.getId());
        map.put("case_summary", ajnx);
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
                            xzInfo = JSON.parseObject(s1, XzInfo.class);
                            rl5.setVisibility(View.VISIBLE);
                            ll1.setVisibility(View.VISIBLE);
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

    private void addImage(String cutPath) {

        String path = NetApi.Path.casepic;
        Map<String, RequestBody> map = new HashMap<>();

        RequestBody b1 = RequestBody.create(MediaType.parse("multipart/form-data"), xzInfo.getId());
        RequestBody b2 = RequestBody.create(MediaType.parse("multipart/form-data"), "add");
        RequestBody b3 = RequestBody.create(MediaType.parse("multipart/form-data"), "android");

        map.put("case_id", b1);
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
                            xzImageInfos.add(xzImageInfo);
                            mIb.addImage(NetApi.baseUrl + xzImageInfo.getFile());
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
}
