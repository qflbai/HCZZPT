package com.yuanxin.hczzpt;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.qflbai.lib.base.activity.BaseActivity;
import com.yuanxin.hczzpt.home.ui.fragment.ManagerFragment;
import com.yuanxin.hczzpt.home.ui.fragment.PlatformsFragment;
import com.yuanxin.hczzpt.platforms.ui.activity.AddXzActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

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
                        finish();
                    }
                });
                inflate.findViewById(R.id.iv_gb).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });

                TextView tvContent = inflate.findViewById(R.id.tv_content);
                tvContent.setText(title+"好");
               /* new XPopup.Builder(mContext)
                        .asConfirm(null, null,
                              null)
                        .bindLayout(R.layout.dialog_exit_login) //绑定已有布局
                        .show();*/
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
