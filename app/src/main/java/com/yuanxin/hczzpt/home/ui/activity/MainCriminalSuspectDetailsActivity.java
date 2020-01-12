package com.yuanxin.hczzpt.home.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.qflbai.lib.base.activity.BaseActivity;
import com.yuanxin.hczzpt.R;
import com.yuanxin.hczzpt.home.adapter.CriminalSuspectAdapter;
import com.yuanxin.hczzpt.home.adapter.ImgAdapter;
import com.yuanxin.hczzpt.home.adapter.PlatformsAdapter;
import com.yuanxin.hczzpt.home.bean.CriminalSuspectInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainCriminalSuspectDetailsActivity extends BaseActivity {
    @BindView(R.id.rv)
    RecyclerView mRv;
    private List<CriminalSuspectInfo> mCriminalSuspectInfos;
    private ImgAdapter mCriminalSuspectAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_criminal_suspect_details);
        initRv();
    }

    private void initRv() {
        mCriminalSuspectInfos = new ArrayList<>();
        mCriminalSuspectAdapter = new ImgAdapter(mContext, mCriminalSuspectInfos);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        mRv.setLayoutManager(linearLayoutManager);
        mRv.setAdapter(mCriminalSuspectAdapter);
    }
}
