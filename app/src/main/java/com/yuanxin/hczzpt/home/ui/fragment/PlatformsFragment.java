package com.yuanxin.hczzpt.home.ui.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.qflbai.lib.base.fragment.BaseFragment;
import com.yuanxin.hczzpt.R;
import com.yuanxin.hczzpt.home.adapter.CriminalSuspectAdapter;
import com.yuanxin.hczzpt.home.adapter.PlatformsAdapter;
import com.yuanxin.hczzpt.home.bean.CriminalSuspectInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author: qflbai
 * @CreateDate: 2020/1/8 19:40
 * @Version: 1.0
 * @description:
 */
public class PlatformsFragment extends BaseFragment {
    @BindView(R.id.rv)
    RecyclerView mRv;
    private List<CriminalSuspectInfo> mCriminalSuspectInfos;
    private PlatformsAdapter mCriminalSuspectAdapter;
    @Override
    protected int setContentResId() {
        return R.layout.fragment_platforms;
    }

    @Override
    protected void initView(Bundle state) {
        super.initView(state);
        initRv();
    }

    private void initRv() {
        mCriminalSuspectInfos = new ArrayList<>();
        mCriminalSuspectAdapter = new PlatformsAdapter(mContext, mCriminalSuspectInfos);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        mRv.setLayoutManager(linearLayoutManager);
        mRv.setAdapter(mCriminalSuspectAdapter);
    }
}
