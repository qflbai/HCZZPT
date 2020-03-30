package com.yuanxin.hczzpt.platforms.ui.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.qflbai.lib.base.fragment.BaseFragment;
import com.qflbai.lib.net.RetrofitManage;
import com.qflbai.lib.net.body.ServerResponseResult;
import com.qflbai.lib.net.callback.NetCallback;
import com.qflbai.lib.net.rxjava.NetObserver;
import com.qflbai.lib.utils.toast.ToastUtil;
import com.yuanxin.hczzpt.R;
import com.yuanxin.hczzpt.common.utils.CommonUtils;
import com.yuanxin.hczzpt.constant.NetApi;
import com.yuanxin.hczzpt.platforms.adapter.XzSelectAdapter;
import com.yuanxin.hczzpt.platforms.bean.BmRyInfo;
import com.yuanxin.hczzpt.platforms.bean.XzSelectInfo;
import com.yuanxin.hczzpt.platforms.request.XtRet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RyFragment extends BaseFragment {

    @BindView(R.id.rv)
    RecyclerView mRv;
    private List<XzSelectInfo> mXzSelectInfos;
    private XzSelectAdapter mXzSelectAdapter;
    private String mId;
    private List<BmRyInfo.TypeBean> mType;

    @Override
    protected int setContentResId() {
        return R.layout.fragment_ry;
    }

    @Override
    protected void initView(Bundle state) {
        super.initView(state);


        mXzSelectInfos = new ArrayList<>();
        mXzSelectAdapter = new XzSelectAdapter(mContext, mXzSelectInfos);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        mRv.setLayoutManager(linearLayoutManager);
        mRv.setAdapter(mXzSelectAdapter);
    }

    @OnClick(R.id.tv_qd)
    public void qd() {


        XtRet xtRets = new XtRet();
       // xtRets.setCase_id(mId);
        List<XtRet.UserBean> userBeans = new ArrayList<>();
        xtRets.setUser(userBeans);
        for (XzSelectInfo xzSelectInfo : mXzSelectInfos) {
            if (xzSelectInfo.isSelect()) {

                BmRyInfo.UserBean userBean = (BmRyInfo.UserBean) xzSelectInfo.getObject();
                BmRyInfo.TypeBean selectType = xzSelectInfo.getSelectType();

                XtRet.UserBean user = new XtRet.UserBean();
                user.setId(userBean.getId());
                user.setType(selectType.getId());

                userBeans.add(user);
            }
        }

        if (userBeans.size() < 1) {
            ToastUtil.showCenter(mContext, "请选择");
            return;
        }


        String jsonString = JSON.toJSONString(xtRets);

        String path = NetApi.Path.casehadd;
        Map<String, Object> map = new HashMap<>();
        map.put("version", "android");
        map.put("jsonString", jsonString);
        map.put("case_id",mId);

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
                            ToastUtil.showCenter(mContext, "邀请成功");
                            finsh();

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

    public void setData(List<BmRyInfo.UserBean> user) {
        for (BmRyInfo.UserBean userBean : user) {
            XzSelectInfo xzSelectInfo = new XzSelectInfo();
            xzSelectInfo.setName(userBean.getName());
            xzSelectInfo.setObject(userBean);
            xzSelectInfo.setTypeBeans(mType);
            mXzSelectInfos.add(xzSelectInfo);
        }

        mXzSelectAdapter.notifyDataSetChanged();
    }

    public void setId(String id) {
        mId = id;
    }

    public void setType(List<BmRyInfo.TypeBean> type) {
        mType = type;
    }
}
