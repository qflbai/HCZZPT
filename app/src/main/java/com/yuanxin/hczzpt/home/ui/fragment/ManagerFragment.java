package com.yuanxin.hczzpt.home.ui.fragment;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.qflbai.lib.base.fragment.BaseFragment;
import com.yuanxin.hczzpt.R;
import com.yuanxin.hczzpt.home.adapter.CriminalSuspectAdapter;
import com.yuanxin.hczzpt.home.bean.CriminalSuspectInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnCheckedChanged;

/**
 * @author: qflbai
 * @CreateDate: 2020/1/8 19:16
 * @Version: 1.0
 * @description:
 */
public class ManagerFragment extends BaseFragment {
    @BindView(R.id.rv)
    RecyclerView rv;
    private List<CriminalSuspectInfo> mCriminalSuspectInfos;
    private CriminalSuspectAdapter mCriminalSuspectAdapter;

    @Override
    protected int setContentResId() {
        return R.layout.fragement_manager;
    }

    @Override
    protected void initView(Bundle state) {
        super.initView(state);
        initRv();
    }

    private void initRv() {
        mCriminalSuspectInfos = new ArrayList<>();
        mCriminalSuspectAdapter = new CriminalSuspectAdapter(mContext, mCriminalSuspectInfos);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(mCriminalSuspectAdapter);
    }
}
