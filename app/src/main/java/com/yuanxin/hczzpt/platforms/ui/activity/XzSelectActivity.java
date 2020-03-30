package com.yuanxin.hczzpt.platforms.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;

import com.alibaba.fastjson.JSON;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.qflbai.lib.base.activity.BaseActivity;
import com.qflbai.lib.net.RetrofitManage;
import com.qflbai.lib.net.body.ServerResponseResult;
import com.qflbai.lib.net.callback.NetCallback;
import com.qflbai.lib.net.rxjava.NetObserver;
import com.yuanxin.hczzpt.R;
import com.yuanxin.hczzpt.common.utils.CommonUtils;
import com.yuanxin.hczzpt.constant.NetApi;
import com.yuanxin.hczzpt.platforms.bean.BmRyInfo;
import com.yuanxin.hczzpt.platforms.bean.XzInfo;
import com.yuanxin.hczzpt.platforms.ui.fragment.BmFragment;
import com.yuanxin.hczzpt.platforms.ui.fragment.RyFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class XzSelectActivity extends BaseActivity {
    @BindView(R.id.tl)
    TabLayout mTl;
    @BindView(R.id.vp2)
    ViewPager2 mVp2;
    private List<Fragment> fragments;
    private BmFragment bmFragment;
    private RyFragment ryFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xz_select);

        initBackToolbar("选择协作部门");
        getIvBack().setImageResource(R.mipmap.ic_bs_fh);

        fragments = new ArrayList<>();

       // mTl.addTab(mTl.newTab().setText("部门选择"));
       // mTl.addTab(mTl.newTab().setText("人员选择"));

        List<String> mTitle = new ArrayList<>();
        mTitle.add("部门选择");
        mTitle.add("人员选择");

        bmFragment = new BmFragment();
        ryFragment = new RyFragment();
        fragments.add(bmFragment);
        fragments.add(ryFragment);

        ViewPagerFragmentStateAdapter viewPagerFragmentStateAdapter = new ViewPagerFragmentStateAdapter(this);
        mVp2.setAdapter(viewPagerFragmentStateAdapter);
        mVp2.setUserInputEnabled(false);
        mVp2.setOffscreenPageLimit(2);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(mTl, mVp2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(mTitle.get(position));

            }
        });

        mTl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if(position==0){
                    initBackToolbar("选择协作部门");
                }else {
                    initBackToolbar("选择协作人员");
                }
                getIvBack().setImageResource(R.mipmap.ic_bs_fh);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        tabLayoutMediator.attach();
        getData();
    }

    private void getData(){
        Intent intent = getIntent();
        String case_id = intent.getStringExtra("case_id");
        String path = NetApi.Path.caseman;
        Map<String, Object> map = new HashMap<>();
        map.put("case_id", case_id);
        map.put("version", "android");

        CommonUtils.removeNull(map);

        showDialogLoading();
        RetrofitManage.newInstance().createService()
                .getNet(path, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetObserver(new NetCallback() {
                    @Override
                    public void onResponse(String s) {

                        hideDialogLoading();

                        ServerResponseResult serverResponseResult = JSON.parseObject(s, ServerResponseResult.class);
                        String code = serverResponseResult.getCode();
                        if ("200".equals(code)) {
                            Object data = serverResponseResult.getData();
                            String s1 = JSON.toJSONString(data);
                            BmRyInfo bmRyInfo = JSON.parseObject(s1, BmRyInfo.class);
                            List<BmRyInfo.DepartBean> depart = bmRyInfo.getDepart();
                            List<BmRyInfo.UserBean> user = bmRyInfo.getUser();
                            List<BmRyInfo.TypeBean> type = bmRyInfo.getType();

                            bmFragment.setType(type);
                            ryFragment.setType(type);

                            bmFragment.setData(depart);
                            bmFragment.setId(case_id);
                            ryFragment.setData(user);
                            ryFragment.setId(case_id);
                        }

                    }

                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        hideDialogLoading();

                    }
                }));
    }

    class ViewPagerFragmentStateAdapter extends FragmentStateAdapter {

        public ViewPagerFragmentStateAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @Override
        public int getItemCount() {
            return fragments.size();
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragments.get(position);
        }
    }


}
