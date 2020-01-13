package com.yuanxin.hczzpt.platforms.ui.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.qflbai.lib.base.activity.BaseActivity;
import com.yuanxin.hczzpt.R;

import butterknife.BindView;
import butterknife.OnClick;

public class AddXyrActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_xyr);

        initBackToolbar("添加嫌疑人");
        getIvBack().setImageResource(R.mipmap.ic_bs_fh);

    }


}
