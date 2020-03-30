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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.qflbai.lib.base.fragment.BaseFragment;
import com.qflbai.lib.net.RetrofitManage;
import com.qflbai.lib.net.body.ServerResponseResult;
import com.qflbai.lib.net.callback.NetCallback;
import com.qflbai.lib.net.rxjava.NetObserver;
import com.qflbai.lib.utils.time.TimeUtils;
import com.qflbai.lib.utils.toast.ToastUtil;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.yuanxin.hczzpt.R;
import com.yuanxin.hczzpt.common.bean.PageInfo;
import com.yuanxin.hczzpt.constant.NetApi;
import com.yuanxin.hczzpt.home.adapter.CriminalSuspectAdapter;
import com.yuanxin.hczzpt.home.adapter.PlatformsAdapter;
import com.yuanxin.hczzpt.home.bean.AjInfo;
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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mSl;
  /*  @BindView(R.id.nice_xz)
    NiceSpinner mSpinner;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_type_dx)
    TextView tvTypeDx;
    @BindView(R.id.tv_type_sd)
    TextView tvTypeSd;*/
    private List<AjInfo.DataBean.ListBean> mCriminalSuspectInfos;
    private PlatformsAdapter mCriminalSuspectAdapter;
    private String sign="";
    private String type="";
    private String time="";
    private String manTag="";

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
                    mSl.resetNoMoreData();
                    String s = mEtSerach.getText().toString();
                    search = s;
                    page=0;
                    time = "";
                    type = "";
                    manTag = "";
                    getData(false);
                    return true;
                }
                return false;
            }
        });

        hideInputManager(mContext,mEtSerach);
    }

    public static void hideInputManager(Context context,View view){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (view !=null && imm != null){
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    protected void loadData() {
        super.loadData();
        page=0;

    }



    private void initRv() {
        mCriminalSuspectInfos = new ArrayList<>();
        mCriminalSuspectAdapter = new PlatformsAdapter(mContext, mCriminalSuspectInfos);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        mRv.setLayoutManager(linearLayoutManager);
        mRv.setAdapter(mCriminalSuspectAdapter);

        mSl.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getData(true);
            }
        });

        mCriminalSuspectAdapter.setOnItemClick(new PlatformsAdapter.OnItemClick() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
        mCriminalSuspectAdapter.setOnBjClick(new PlatformsAdapter.OnItemClick() {
            @Override
            public void onItemClick(View view, int position) {
                AjInfo.DataBean.ListBean listBean = mCriminalSuspectInfos.get(position);
                String id = listBean.getId();
                Intent intent = new Intent(mContext, TeamDetailsActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
               /* Intent intent = new Intent(mContext, AddXzActivity.class);
                startActivity(intent);*/
            }
        });

        mCriminalSuspectAdapter.setOnAjXzClick(new PlatformsAdapter.OnItemSelectClick() {
            @Override
            public void onItemClick(String value) {
                mSl.resetNoMoreData();
                type = value;
                page=0;
                manTag = "";
                getData(false);
            }
        });

        mCriminalSuspectAdapter.setOnTimeClick(new PlatformsAdapter.OnItemSelectClick() {
            @Override
            public void onItemClick(String value) {
                mSl.resetNoMoreData();
                time = value;
                page=0;
                manTag = "";
                getData(false);
            }
        });

        mCriminalSuspectAdapter.setOnItemFlBq(new PlatformsAdapter.OnItemSelectClick() {
            @Override
            public void onItemClick(String value) {
                mSl.resetNoMoreData();
                time = "";
                page = 0;
                type = "";
                manTag = value;
                getData(false);
            }
        });

        mCriminalSuspectAdapter.setOnDeleteClick(new CriminalSuspectAdapter.OnItemClick() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
    }

    int page = 0;
    String search = "";
    private void getData(boolean isMore){
        String path = NetApi.Path.caseInfo;
        Map<String, Object> map = new HashMap<>();
        map.put("page",page);
        map.put("search",search);
        map.put("sign",sign);
        map.put("type",type);
        map.put("time",time);
        map.put("man_tag",manTag);
        map.put("version","android");

        if(!isMore) {
            showDialogLoading();
        }
        RetrofitManage.newInstance().createService()
                .getNet(path, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetObserver(new NetCallback() {
                    @Override
                    public void onResponse(String s) {
                        if(isMore){
                            mSl.finishLoadMore();
                        }else {
                            hideDialogLoading();
                            mCriminalSuspectInfos.clear();
                            mCriminalSuspectAdapter.notifyDataSetChanged();
                        }

                        ServerResponseResult serverResponseResult = JSON.parseObject(s, ServerResponseResult.class);
                        String code = serverResponseResult.getCode();
                        if("200".equals(code)){
                            Object data = serverResponseResult.getData();
                            String s1 = JSON.toJSONString(data);
                            AjInfo  ajInfo = JSON.parseObject(s1, AjInfo.class);
                            AjInfo.DataBean ajInfoData = ajInfo.getData();
                            if(isMore){
                                int pageCount = ajInfoData.getPageCount();
                                if(pageCount<=page){
                                    mSl.finishLoadMoreWithNoMoreData();
                                    return;
                                }
                            }
                            List<AjInfo.DataBean.ListBean> list = ajInfoData.getList();
                            mCriminalSuspectInfos.addAll(list);
                            List<AjInfo.DataBean.CaseTypeBean> case_type = ajInfoData.getCase_type();
                            mCriminalSuspectAdapter.setSpinnerData(case_type);
                            mCriminalSuspectAdapter.notifyDataSetChanged();
                        }

                    }

                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        if(!isMore){
                            hideDialogLoading();
                        }

                    }
                }));
    }

    @Override
    public void onResume() {
        super.onResume();
        page = 0;
        time= "";
        sign="";
        type="";
        search = "";
        manTag="";
        mSl.resetNoMoreData();
        getData(false);
    }
}
