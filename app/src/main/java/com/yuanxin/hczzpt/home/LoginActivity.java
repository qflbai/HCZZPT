package com.yuanxin.hczzpt.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.qflbai.lib.base.activity.BaseActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.vondear.rxui.view.dialog.RxDialogSureCancel;
import com.yuanxin.hczzpt.MainActivity;
import com.yuanxin.hczzpt.R;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.root)
    View root;
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
    }

    /**
     * 权限申请
     */
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
                            //只要有一个权限被拒绝，就会执行
                            // AlertDialog alertDialog = new AlertDialog.Builder(mContext).setTitle("温馨提示");
                            RxDialogSureCancel rxDialog = new RxDialogSureCancel(mContext);
                            rxDialog.setTitle("温馨提示");
                            rxDialog.setContent("使用该APP，你需要授权!");
                            rxDialog.setSureListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    rxDialog.dismiss();
                                    applyForPermissions();
                                }
                            });
                            rxDialog.setCancelListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    finish();
                                }
                            });
                            rxDialog.setCanceledOnTouchOutside(false);
                            rxDialog.show();
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
        Intent intent = new Intent(mContext, MainActivity.class);
        startActivity(intent);
    }
}
