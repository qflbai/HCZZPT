package com.yuanxin.hczzpt.platforms.ui.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.qflbai.lib.base.activity.BaseActivity;
import com.yuanxin.hczzpt.R;

import butterknife.BindView;
import butterknife.OnClick;

public class AddXzActivity extends BaseActivity {
    @BindView(R.id.ll_add_xyr)
    LinearLayout mLLAddXyr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_xz);

        initBackToolbar("案件详情");
        getIvBack().setImageResource(R.mipmap.ic_bs_fh);
    }

    @OnClick(R.id.tv_add_xyr)
    public void addXyr() {

        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_recycler_platforms_team_xyr_info, null);


        mLLAddXyr.addView(inflate);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) inflate.getLayoutParams();
        layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
        layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;

    }
}
