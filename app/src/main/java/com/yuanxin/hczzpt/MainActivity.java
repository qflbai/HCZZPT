package com.yuanxin.hczzpt;


import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.qflbai.lib.base.activity.BaseActivity;
import com.yuanxin.hczzpt.home.ui.fragment.ManagerFragment;
import com.yuanxin.hczzpt.home.ui.fragment.PlatformsFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @BindView(R.id.ll_gl)
    LinearLayout mLLgl;
    @BindView(R.id.ll_pt)
    LinearLayout mLLpt;
    private ManagerFragment managerFragment;
    private PlatformsFragment platformsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        if (managerFragment == null) {
            managerFragment = new ManagerFragment();
        }
        fragmentTransaction.replace(R.id.rl, managerFragment);
        fragmentTransaction.commit();
    }


    @OnClick({R.id.ll_gl, R.id.ll_pt})
    public void switchFragement(View v) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        if (v.getId() == R.id.ll_gl) {
            setDhBg(true, mLLgl);
            setDhBg(false, mLLpt);

            if (managerFragment == null) {
                managerFragment = new ManagerFragment();
            }
            fragmentTransaction.replace(R.id.rl, managerFragment);
            fragmentTransaction.commit();
        } else {
            setDhBg(false, mLLgl);
            setDhBg(true, mLLpt);
            if (platformsFragment == null) {
                platformsFragment = new PlatformsFragment();
            }
            fragmentTransaction.replace(R.id.rl, platformsFragment);
            fragmentTransaction.commit();
            ;
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
