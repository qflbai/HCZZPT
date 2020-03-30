package com.yuanxin.hczzpt;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.alibaba.fastjson.JSON;
import com.qflbai.lib.base.activity.BaseActivity;
import com.qflbai.lib.net.RetrofitManage;
import com.qflbai.lib.net.body.ServerResponseResult;
import com.qflbai.lib.net.callback.NetCallback;
import com.qflbai.lib.net.rxjava.NetObserver;
import com.qflbai.lib.net.url.BaseNetApi;
import com.qflbai.lib.utils.sharedpreferences.SpUtil;
import com.qflbai.lib.utils.toast.ToastUtil;
import com.yuanxin.hczzpt.constant.NetApi;
import com.yuanxin.hczzpt.home.bean.UserInfo;
import com.yuanxin.hczzpt.home.ui.fragment.ManagerFragment;
import com.yuanxin.hczzpt.home.ui.fragment.PlatformsFragment;
import com.yuanxin.hczzpt.platforms.ui.activity.AddXzActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity {
    @BindView(R.id.ll_gl)
    LinearLayout mLLgl;
    @BindView(R.id.ll_pt)
    LinearLayout mLLpt;
    private ManagerFragment managerFragment;
    private PlatformsFragment platformsFragment;
    private TextView tvSubtitleTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            managerFragment = (ManagerFragment) getSupportFragmentManager().findFragmentByTag(ManagerFragment.class.getName());
            platformsFragment = (PlatformsFragment) getSupportFragmentManager().findFragmentByTag(PlatformsFragment.class.getName());
            getSupportFragmentManager().beginTransaction()
                    .show(managerFragment)
                    .hide(platformsFragment)
                    .commit();
        } else {

            managerFragment = new ManagerFragment();
            platformsFragment = new PlatformsFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.rl, managerFragment, ManagerFragment.class.getName())
                    .add(R.id.rl, platformsFragment, PlatformsFragment.class.getName())
                    .hide(platformsFragment)
                    .commit();
        }
        tvSubtitleTitle = getTvSubtitleTitle();
        tvSubtitleTitle.setText("添加协作");
        tvSubtitleTitle.setVisibility(View.GONE);
        tvSubtitleTitle.setTextColor(getResources().getColor(R.color.white));
        tvSubtitleTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AddXzActivity.class);
                startActivity(intent);
            }
        });

        setToolbarTitle("嫌疑人管理");
        ImageView ivBack = getIvBack();
        ivBack.setImageResource(R.mipmap.ic_she_zhi);
        getLlBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
    }

    private void back(){
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("HH");
        String str = df.format(date);
        int a = Integer.parseInt(str);
        String title = "";
        if (a >= 0 && a <= 6) {
            title = "凌晨";
        }
        if (a > 6 && a <= 12) {
            title = "上午";
        }
        if (a > 12 && a <= 13) {
            title = "中午";
        }
        if (a > 13 && a <= 18) {
            title = "下午";
        }
        if (a > 18 && a <= 24) {
            title = "晚上";
        }

        View inflate = LayoutInflater.from(mContext).inflate(R.layout.dialog_exit_login, null);


        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setView(inflate);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        inflate.findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                outLogin();

            }
        });
        inflate.findViewById(R.id.iv_gb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        TextView tvContent = inflate.findViewById(R.id.tv_content);
        tvContent.setText(App.userName+":"+title+"好");
    }


    @OnClick({R.id.ll_gl, R.id.ll_pt})
    public void switchFragement(View v) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        if (v.getId() == R.id.ll_gl) {
            setDhBg(true, mLLgl);
            setDhBg(false, mLLpt);
            setToolbarTitle("嫌疑人管理");
            tvSubtitleTitle.setVisibility(View.GONE);

            fragmentTransaction.show(managerFragment);
            fragmentTransaction.hide(platformsFragment);
            fragmentTransaction.commit();


        } else {
            setDhBg(false, mLLgl);
            setDhBg(true, mLLpt);
            setToolbarTitle("案件协作平台");
            tvSubtitleTitle.setVisibility(View.VISIBLE);
            fragmentTransaction.show(platformsFragment);
            fragmentTransaction.hide(managerFragment);
            fragmentTransaction.commit();

        }
    }

    private void setDhBg(boolean isSelect, View v) {

        if (isSelect) {
            v.setBackgroundColor(getResources().getColor(R.color._003A7C));

        } else {
            v.setBackgroundColor(getResources().getColor(R.color._0068DE));
        }
    }

    private void outLogin(){

        String path = NetApi.Path.loginout;


        showDialogLoading();
        RetrofitManage.newInstance().createService()
                .getNet(path)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetObserver(new NetCallback() {
                    @Override
                    public void onResponse(String s) {
                        hideDialogLoading();
                        ServerResponseResult serverResponseResult = JSON.parseObject(s, ServerResponseResult.class);
                        String code = serverResponseResult.getCode();
                        if("200".equals(code)){
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

    @Override
    public void onBackPressed() {
        back();
    }
}
