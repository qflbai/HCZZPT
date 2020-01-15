package com.yuanxin.hczzpt.home.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.qflbai.lib.base.fragment.BaseFragment;
import com.qflbai.lib.utils.time.TimeUtils;
import com.qflbai.lib.utils.toast.ToastUtil;
import com.yuanxin.hczzpt.R;
import com.yuanxin.hczzpt.home.adapter.CriminalSuspectAdapter;
import com.yuanxin.hczzpt.home.adapter.PlatformsAdapter;
import com.yuanxin.hczzpt.home.bean.CriminalSuspectInfo;
import com.yuanxin.hczzpt.platforms.ui.activity.AddXzActivity;
import com.yuanxin.hczzpt.platforms.ui.activity.TeamDetailsActivity;
import com.yuanxin.hczzpt.time.TimePickerUtil;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author: qflbai
 * @CreateDate: 2020/1/8 19:40
 * @Version: 1.0
 * @description:
 */
public class PlatformsFragment extends BaseFragment {
    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.et_search)
    EditText mEtSerach;
  /*  @BindView(R.id.nice_xz)
    NiceSpinner mSpinner;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_type_dx)
    TextView tvTypeDx;
    @BindView(R.id.tv_type_sd)
    TextView tvTypeSd;*/
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
        mEtSerach.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    InputMethodManager systemService = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (systemService.isActive()) {
                        systemService.hideSoftInputFromWindow(v.getApplicationWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
                    }

                    return true;
                }
                return false;
            }
        });
    }

    private void initRv() {
        mCriminalSuspectInfos = new ArrayList<>();
        mCriminalSuspectAdapter = new PlatformsAdapter(mContext, mCriminalSuspectInfos);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        mRv.setLayoutManager(linearLayoutManager);
        mRv.setAdapter(mCriminalSuspectAdapter);
        mCriminalSuspectAdapter.setOnItemClick(new PlatformsAdapter.OnItemClick() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(mContext, TeamDetailsActivity.class);
                startActivity(intent);
            }
        });
        mCriminalSuspectAdapter.setOnBjClick(new PlatformsAdapter.OnItemClick() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(mContext, AddXzActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public void onResume() {
        super.onResume();

    }
}
