package com.yuanxin.hczzpt.home.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.bumptech.glide.load.model.ModelLoader;
import com.qflbai.lib.base.fragment.BaseFragment;
import com.qflbai.lib.net.RetrofitManage;
import com.qflbai.lib.net.body.ServerResponseResult;
import com.qflbai.lib.net.callback.NetCallback;
import com.qflbai.lib.net.rxjava.NetObserver;
import com.qflbai.lib.net.url.BaseNetApi;
import com.qflbai.lib.utils.toast.ToastUtil;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.yuanxin.hczzpt.MainActivity;
import com.yuanxin.hczzpt.R;
import com.yuanxin.hczzpt.common.bean.PageInfo;
import com.yuanxin.hczzpt.constant.NetApi;
import com.yuanxin.hczzpt.event.EventAction;
import com.yuanxin.hczzpt.event.EventMessage;
import com.yuanxin.hczzpt.home.adapter.CriminalSuspectAdapter;
import com.yuanxin.hczzpt.home.bean.CriminalSuspectInfo;
import com.yuanxin.hczzpt.home.bean.UserInfo;
import com.yuanxin.hczzpt.home.ui.activity.MainCriminalSuspectDetailsActivity;
import com.yuanxin.hczzpt.home.ui.activity.XyrBjActivity;
import com.yuanxin.hczzpt.platforms.ui.activity.AddXyrActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: qflbai
 * @CreateDate: 2020/1/8 19:16
 * @Version: 1.0
 * @description:
 */
public class ManagerFragment extends BaseFragment {
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.et_search)
    EditText mEtSerach;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mSl;
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

        mEtSerach.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId== EditorInfo.IME_ACTION_SEARCH){
                    InputMethodManager systemService = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if(systemService.isActive()){
                        systemService.hideSoftInputFromWindow(v.getApplicationWindowToken(),InputMethodManager.RESULT_UNCHANGED_SHOWN);
                    }
                    mSl.resetNoMoreData();
                    String s = mEtSerach.getText().toString();
                    search = s;
                    page=0;
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
    protected void loadData() {

    }

    int page = 0;
    String search = "";
    private void getData(boolean isMore){


        String path = NetApi.Path.manlist;
        Map<String, Object> map = new HashMap<>();
        map.put("page",page);
        map.put("search",search);
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
                            PageInfo pageInfo = JSON.parseObject(s1, PageInfo.class);
                            if(isMore){
                                int pageCount = pageInfo.getPageCount();
                                if(pageCount<=page){
                                    mSl.finishLoadMoreWithNoMoreData();
                                    return;
                                }
                            }
                            Object list = pageInfo.getList();
                            String s2 = JSON.toJSONString(list);
                            List<CriminalSuspectInfo> criminalSuspectInfos = JSON.parseArray(s2, CriminalSuspectInfo.class);
                            mCriminalSuspectInfos.addAll(criminalSuspectInfos);
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

    private void initRv() {
        mCriminalSuspectInfos = new ArrayList<>();
        mCriminalSuspectAdapter = new CriminalSuspectAdapter(mContext, mCriminalSuspectInfos);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(mCriminalSuspectAdapter);

        mCriminalSuspectAdapter.setOnCkXqClick(new CriminalSuspectAdapter.OnItemClick() {
            @Override
            public void onItemClick(View view, int position) {
                CriminalSuspectInfo criminalSuspectInfo = mCriminalSuspectInfos.get(position);

                Intent intent = new Intent(mContext, MainCriminalSuspectDetailsActivity.class);
                startActivity(intent);
                EventBus.getDefault().postSticky(criminalSuspectInfo);
            }
        });

        mCriminalSuspectAdapter.setOnBjClick(new CriminalSuspectAdapter.OnItemClick() {
            @Override
            public void onItemClick(View view, int position) {
                CriminalSuspectInfo criminalSuspectInfo = mCriminalSuspectInfos.get(position);
                String id = criminalSuspectInfo.getId();

                Intent intent = new Intent(mContext, XyrBjActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

        mCriminalSuspectAdapter.setOnDeleteClick(new CriminalSuspectAdapter.OnItemClick() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });

        mSl.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getData(true);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        page=0;
        search="";
        mSl.resetNoMoreData();
        getData(false);
    }
}
