package com.yuanxin.hczzpt.home.ui.activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.qflbai.lib.base.activity.BaseActivity;
import com.qflbai.lib.net.RetrofitManage;
import com.qflbai.lib.net.body.ServerResponseResult;
import com.qflbai.lib.net.callback.NetCallback;
import com.qflbai.lib.net.rxjava.NetObserver;
import com.yuanxin.hczzpt.R;
import com.yuanxin.hczzpt.constant.NetApi;
import com.yuanxin.hczzpt.home.adapter.ImgAdapter;
import com.yuanxin.hczzpt.home.bean.CriminalSuspectDetailsInfo;
import com.yuanxin.hczzpt.home.bean.CriminalSuspectInfo;
import com.yuanxin.hczzpt.home.bean.ZpjlTpInfo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainCriminalSuspectDetailsActivity extends BaseActivity {
    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.iv_tx)
    ImageView mIvTx;
    @BindView(R.id.tv_xyr_id)
    TextView mTvXyrId;
    @BindView(R.id.tv_xyr)
    TextView mTvXyr;
    @BindView(R.id.tv_xyr_sfz)
    TextView mTvXyrSfz;
    @BindView(R.id.tv_wxh)
    TextView mTvWxh;
    @BindView(R.id.tv_cph)
    TextView mTvCph;
    @BindView(R.id.tv_sjhm)
    TextView mTvSjhm;

    private List<ZpjlTpInfo> mZpjlTpInfos;
    private ImgAdapter mImageAdapter;
    private String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_main_criminal_suspect_details);

        initBackToolbar("嫌疑人资料");
        getIvBack().setImageResource(R.mipmap.ic_bs_fh);

        initRv();
        getData();
    }

    private void getData() {
        String path = NetApi.Path.maninfo;
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("version", "android");
        showLoading();
        RetrofitManage.newInstance().createService()
                .getNet(path, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetObserver(new NetCallback() {
                    @Override
                    public void onResponse(String s) {
                        showContentView();

                        ServerResponseResult serverResponseResult = JSON.parseObject(s, ServerResponseResult.class);
                        String code = serverResponseResult.getCode();
                        if ("200".equals(code)) {
                            Object data = serverResponseResult.getData();
                            String s1 = JSON.toJSONString(data);
                            CriminalSuspectDetailsInfo criminalSuspectDetailsInfo = JSON.parseObject(s1, CriminalSuspectDetailsInfo.class);
                            List<CriminalSuspectDetailsInfo.ExtBean> ext = criminalSuspectDetailsInfo.getExt();
                            CriminalSuspectDetailsInfo.InfoBean info = criminalSuspectDetailsInfo.getInfo();

                            if (info != null) {
                                mTvXyrId.setText(info.getNumber_id());
                                mTvXyr.setText(info.getName());
                                mTvXyrSfz.setText(info.getId_card());
                                mTvWxh.setText(info.getId_wechat());
                                mTvCph.setText(info.getId_car());
                                mTvSjhm.setText(info.getPhone());
                            }
                            if (ext != null && ext.size() > 0) {

                                for (int i = 0; i < ext.size(); i++) {
                                    CriminalSuspectDetailsInfo.ExtBean extBean = ext.get(i);

                                    String action_images = extBean.getAction_images();

                                    ZpjlTpInfo zpjlTpInfoTile = new ZpjlTpInfo();
                                    zpjlTpInfoTile.setExtBean(extBean);
                                    mZpjlTpInfos.add(zpjlTpInfoTile);

                                    if (action_images != null && !TextUtils.isEmpty(action_images)) {
                                        String[] split = action_images.split(",");
                                        List<String> strings = Arrays.asList(split);
                                        for (String iamgeString : strings) {
                                            String replace = iamgeString.replace("\\", "");
                                            String url = NetApi.baseUrl + "/" + replace;
                                            ZpjlTpInfo zpjlTpInfo = new ZpjlTpInfo();
                                            zpjlTpInfo.setIamgeUrl(url);
                                            mZpjlTpInfos.add(zpjlTpInfo);
                                        }
                                    }
                                }
                                mImageAdapter.notifyDataSetChanged();

                            }
                        }

                    }

                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        showError();

                    }
                }));
    }

    @Subscribe(sticky = true)
    public void getCriminalSuspectInfo(CriminalSuspectInfo criminalSuspectInfo) {
        id = criminalSuspectInfo.getId();
    }

    private void initRv() {
        mZpjlTpInfos = new ArrayList<>();
        mImageAdapter = new ImgAdapter(mContext, mZpjlTpInfos);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        mRv.setLayoutManager(linearLayoutManager);
        mRv.setAdapter(mImageAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.tv_fh)
    public void FanHui() {
        finish();
    }
}
