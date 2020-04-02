package com.yuanxin.hczzpt.platforms.ui.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.leon.lfilepickerlibrary.LFilePicker;
import com.leon.lfilepickerlibrary.utils.Constant;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.qflbai.lib.base.activity.BaseActivity;
import com.qflbai.lib.base.data.ViewState;
import com.qflbai.lib.net.RetrofitManage;
import com.qflbai.lib.net.body.ServerResponseResult;
import com.qflbai.lib.net.callback.NetCallback;
import com.qflbai.lib.net.rxjava.NetObserver;
import com.qflbai.lib.ui.widget.image.ImageBox;
import com.qflbai.lib.utils.toast.ToastUtil;
import com.yuanxin.hczzpt.R;
import com.yuanxin.hczzpt.common.utils.CommonUtils;
import com.yuanxin.hczzpt.common.utils.ImageBoxX;
import com.yuanxin.hczzpt.common.utils.RetrofitApi;
import com.yuanxin.hczzpt.constant.NetApi;
import com.yuanxin.hczzpt.image.GlideEngine;
import com.yuanxin.hczzpt.platforms.bean.XzInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class AddYzxzActivity extends BaseActivity {

    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.ib)
    ImageBox mIb;
    @BindView(R.id.ll_1)
    RelativeLayout ll1;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    private String mId;
    private ArrayList<String> xzImageInfos;
    private ArrayList<String> fileIds;
    private String yzId;
    private boolean isAddImageAble = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_yzxz);
        initBackToolbar("添加研制信息");
        getIvBack().setImageResource(R.mipmap.ic_bs_fh);
        Intent intent = getIntent();
        mId = intent.getStringExtra("id");
        initIb();
    }

    private void initIb() {
        xzImageInfos = new ArrayList<>();
        fileIds = new ArrayList<>();
        mIb.setDefaultPicId(R.mipmap.ic_wjj);
        mIb.setOnlineImageLoader(new ImageBox.OnlineImageLoader() {
            @Override
            public void onLoadImage(ImageView iv, String url) {
               Glide.with(mContext).load(R.mipmap.ic_wjj).into(iv);
              //  iv.setImageResource(R.mipmap.ic_wjj);
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
                xzImageInfos.remove(position);
            }

            @Override
            public void onAddClick() {
                if (isAddImageAble) {
                    chooseImage();
                } else {
                    ToastUtil.showCenter(mContext, "已提交,不能添加图片");
                }
            }
        });

        mIb.setShowTitle(new ImageBox.TitleShow() {
            @Override
            public void TitleShow(int i, View view) {
                TextView title = (TextView) view;
                title.setMaxLines(1);
                title.setSingleLine(true);
                title.setEllipsize(TextUtils.TruncateAt.START);
                if(i<xzImageInfos.size()) {
                    String s = xzImageInfos.get(i);
                    File file = new File(s);
                    title.setVisibility(View.VISIBLE);
                    title.setText(file.getName());
                }else {
                    title.setVisibility(View.GONE);
                }
            }
        });
    }

    public void chooseImage() {
        new LFilePicker()
                .withActivity(AddYzxzActivity.this)
                .withTheme(R.style.LfpTheme)
                .withTitle("选择附件")
                .withRequestCode(200)
                .start();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 200) {
                List<String> list = data.getStringArrayListExtra(Constant.RESULT_INFO);
                xzImageInfos.addAll(list);

                mIb.addImages(list);

            }
        }
    }

    @OnClick(R.id.tv_submit)
    public void submit() {
        String content = etContent.getText().toString();
        if (TextUtils.isEmpty(content)) {
            ToastUtil.showCenter(mContext, "填写信息");
            return;
        }
        String path = NetApi.Path.casemadd;
        Map<String, Object> map = new HashMap<>();
        map.put("case_id", mId);
        map.put("summary", content);
        map.put("version", "android");

        String files = CommonUtils.dataAddDhString(this.fileIds);

        map.put("files",files);
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
                            XzInfo xzInfo = JSON.parseObject(JSON.toJSONString(serverResponseResult.getData()), XzInfo.class);
                            yzId = xzInfo.getId();
                           // tvSubmit.setVisibility(View.GONE);
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

    @OnClick(R.id.tv_add)
    public void add() {

        /*if (yzId == null) {
            ToastUtil.showCenter(mContext, "请先提交");
            return;
        }*/

        if (xzImageInfos.size() < 1) {
            ToastUtil.showCenter(mContext, "请添加附件");
            return;
        }

        showDialogLoading();

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                for (String filePath : xzImageInfos) {
                    String path = NetApi.Path.casehfile;
                    Map<String, RequestBody> map = new HashMap<>();

                    RequestBody b1 = RequestBody.create(MediaType.parse("multipart/form-data"), "addFile");
                   // RequestBody b2 = RequestBody.create(MediaType.parse("multipart/form-data"), yzId);
                    RequestBody b3 = RequestBody.create(MediaType.parse("multipart/form-data"), "android");


                    map.put("action", b1);
                    //map.put("msg_id", b2);
                    map.put("version", b3);

                    File file = new File(filePath);
                    RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                    String name = file.getName();

                    MultipartBody.Part part = MultipartBody.Part.createFormData("file", name, imageBody);

                    RetrofitManage.newInstance().createService(RetrofitApi.class)
                            .uplodaOne(path, map, part)
                            .subscribe(new Consumer<Response<ResponseBody>>() {
                                @Override
                                public void accept(Response<ResponseBody> responseBodyResponse) throws Exception {
                                    boolean successful = responseBodyResponse.isSuccessful();
                                    if (successful) {
                                        int code = responseBodyResponse.code();
                                        if (code == 200) {
                                            try {

                                                String jsonString = ((ResponseBody) responseBodyResponse.body()).string();
                                                ServerResponseResult serverResponseResult = JSON.parseObject(jsonString, ServerResponseResult.class);

                                                Object data = serverResponseResult.getData();
                                                String s1 = JSON.toJSONString(data);

                                                XzImageInfo xzImageInfo = JSON.parseObject(s1, XzImageInfo.class);
                                                String url = NetApi.baseUrl + xzImageInfo.getFile();
                                                xzImageInfo.setFile(url);
                                                fileIds.add(xzImageInfo.getId());
                                            } catch (Exception throwable) {
                                                throwable.printStackTrace();
                                                emitter.onError(throwable);
                                            }
                                        } else {
                                            emitter.onError(new Throwable());
                                        }
                                    }
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    emitter.onError(throwable);
                                    emitter.onComplete();
                                }
                            });

                }

                emitter.onNext("");
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        isAddImageAble = false;
                        mIb.setDeletable(false);
                        hideDialogLoading();
                        ToastUtil.showCenter(mContext,"上传成功");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ToastUtil.showCenter(mContext, "上传失败");
                        hideDialogLoading();
                    }
                });


    }
}
