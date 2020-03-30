package com.yuanxin.hczzpt.home;

import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.gyf.immersionbar.ImmersionBar;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnCancelListener;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.qflbai.lib.base.activity.BaseActivity;
import com.qflbai.lib.net.RetrofitManage;

import com.qflbai.lib.net.body.ServerResponseResult;
import com.qflbai.lib.net.callback.NetCallback;
import com.qflbai.lib.net.rxjava.NetObserver;
import com.qflbai.lib.net.url.BaseNetApi;
import com.qflbai.lib.utils.sharedpreferences.SpUtil;
import com.qflbai.lib.utils.toast.ToastUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.yuanxin.hczzpt.App;
import com.yuanxin.hczzpt.MainActivity;
import com.yuanxin.hczzpt.R;
import com.yuanxin.hczzpt.constant.NetApi;
import com.yuanxin.hczzpt.home.bean.UserInfo;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.root)
    View root;
    @BindView(R.id.et_username)
    EditText etName;
    @BindView(R.id.et_pw)
    EditText etPw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = getToolbar();
        toolbar.setBackgroundColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        setToolbarTitle("登录");
        TextView titleTextView = getTitleTextView();
        titleTextView.setTextColor(getResources().getColor(R.color._191919));
        ImageView ivBack = this.getIvBack();
        ivBack.setImageResource(R.mipmap.ic_fan_hui);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        applyForPermissions();
    }

    @Override
    public void initData() {
        root.setVisibility(View.VISIBLE);
        String login_user = SpUtil.getString(mContext, "login_user", "");
        etName.setText(login_user);
    }

    /**
     * 权限申请
     */
    @SuppressLint("CheckResult")
    private void applyForPermissions() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            //申请的权限全部允许
                            initData();
                        } else {

                            new XPopup.Builder(mContext)
                                    .asConfirm("温馨提示", "使用该APP，你需要授权!","取消","确定",
                                            new OnConfirmListener() {
                                                @Override
                                                public void onConfirm() {
                                                    applyForPermissions();
                                                }
                                            }, new OnCancelListener() {
                                                @Override
                                                public void onCancel() {

                                                }
                                            }, false)
                                    .bindLayout(R.layout.dialog_sure_false) //绑定已有布局
                                    .show();

                        }
                    }
                });
    }

    @Override
    protected void initStatusBar() {
        ImmersionBar.with(this)
                .titleBar(getToolbar())
                .statusBarDarkFont(true)
                .init();
    }

    @OnClick(R.id.tv_login)
    public void login(){

        String  name= etName.getText().toString().trim();
        String pw = etPw.getText().toString().trim();
        if(TextUtils.isEmpty(name)||TextUtils.isEmpty(pw)){
            ToastUtil.show(mContext,"请填写账号或密码");
            return;
        }

        String path = NetApi.Path.login;
        Map<String, Object> map = new HashMap<>();
        map.put("user_name",name);
        map.put("user_pass",pw);

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
                        if("200".equals(code)){
                            Object data = serverResponseResult.getData();
                            String s1 = JSON.toJSONString(data);
                            UserInfo userInfo = JSON.parseObject(s1, UserInfo.class);
                            App.userName = userInfo.getUser_name();
                            String token = userInfo.getToken();
                            String refresh = userInfo.getRefresh();
                            BaseNetApi.setToken(token);
                            BaseNetApi.setJwtRefresh(refresh);
                            SpUtil.putString(mContext,"login_user",name);
                            Intent intent = new Intent(mContext, MainActivity.class);
                            startActivity(intent);
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
}
