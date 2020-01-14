package com.yuanxin.hczzpt;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.qflbai.lib.base.activity.BaseActivity;
import com.vondear.rxui.view.dialog.RxDialogSureCancel;
import com.yuanxin.hczzpt.home.ui.fragment.ManagerFragment;
import com.yuanxin.hczzpt.home.ui.fragment.PlatformsFragment;
import com.yuanxin.hczzpt.platforms.ui.activity.AddXzActivity;
import com.yuanxin.hczzpt.platforms.ui.activity.TeamDetailsActivity;

import butterknife.BindView;
import butterknife.OnClick;

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
                RxDialogSureCancel rxDialogSureCance = new RxDialogSureCancel(mContext);
                rxDialogSureCance.setCancel("取消");
                rxDialogSureCance.setSure("确定");
                rxDialogSureCance.setTitle("是否退出登录");
                rxDialogSureCance.setSureListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        rxDialogSureCance.dismiss();
                        finish();
                    }
                });
                rxDialogSureCance.setCancelListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        rxDialogSureCance.dismiss();
                    }
                });

                rxDialogSureCance.show();
            }
        });
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


}
