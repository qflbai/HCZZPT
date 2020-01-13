package com.yuanxin.hczzpt.platforms.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.SystemClock;

import com.chad.library.adapter.base.listener.GridSpanSizeLookup;
import com.qflbai.lib.base.activity.BaseActivity;
import com.qflbai.lib.utils.StringUtils;
import com.yuanxin.hczzpt.R;
import com.yuanxin.hczzpt.platforms.adapter.NormalMultipleEntity;
import com.yuanxin.hczzpt.platforms.adapter.TeamDetailsAdapter;
import com.yuanxin.hczzpt.platforms.adapter.provider.CjrInfoProvider;
import com.yuanxin.hczzpt.platforms.adapter.provider.CsInfoProvider;
import com.yuanxin.hczzpt.platforms.adapter.provider.FuJianImgProvider;
import com.yuanxin.hczzpt.platforms.adapter.provider.LawCaseDetailsProvider;
import com.yuanxin.hczzpt.platforms.adapter.provider.LawCaseInfoProvider;
import com.yuanxin.hczzpt.platforms.adapter.provider.TitleFjProvider;
import com.yuanxin.hczzpt.platforms.adapter.provider.TitleFjTimeProvider;
import com.yuanxin.hczzpt.platforms.adapter.provider.TitleProvider;
import com.yuanxin.hczzpt.platforms.adapter.provider.YanZhiInfoProvider;
import com.yuanxin.hczzpt.platforms.bean.FujianTimeTitleInfo;
import com.yuanxin.hczzpt.platforms.bean.TeamTitleInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;

public class TeamDetailsActivity extends BaseActivity {
    @BindView(R.id.rv)
    RecyclerView mRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_details);
        initRv();
        initBackToolbar("案件详情");
        getIvBack().setImageResource(R.mipmap.ic_bs_fh);
    }


    private void initRv() {
        GridLayoutManager manager = new GridLayoutManager(mContext, 4);
        mRv.setLayoutManager(manager);


        List<NormalMultipleEntity> list = new ArrayList<>();

        TeamTitleInfo ajxq = new TeamTitleInfo();
        ajxq.setTitle("案件详情");
        NormalMultipleEntity titleAjxq = new NormalMultipleEntity(NormalMultipleEntity.TITLE,ajxq);
        list.add(titleAjxq);

        list.add(new NormalMultipleEntity(NormalMultipleEntity.LAW_CASE_DETAILS_ITEM));

        TeamTitleInfo cjr = new TeamTitleInfo();
        cjr.setTitle("创建人");
        NormalMultipleEntity titleCjr = new NormalMultipleEntity(NormalMultipleEntity.TITLE,cjr);
        list.add(titleCjr);


        list.add(new NormalMultipleEntity(NormalMultipleEntity.CJR_INFO));

        TeamTitleInfo ajnx = new TeamTitleInfo();
        ajnx.setTitle("案件内信");
        NormalMultipleEntity titleAjnx = new NormalMultipleEntity(NormalMultipleEntity.TITLE,ajnx);
        list.add(titleAjnx);

        list.add(new NormalMultipleEntity(NormalMultipleEntity.LAW_CASE_INFO));

        TeamTitleInfo sdryxx = new TeamTitleInfo();
        sdryxx.setShowAdd(true);
        sdryxx.setTitle("涉毒人员信息");
        NormalMultipleEntity titleSdryxx = new NormalMultipleEntity(NormalMultipleEntity.TITLE,sdryxx);
        list.add(titleSdryxx);

        list.add(new NormalMultipleEntity(NormalMultipleEntity.XYR_INFO));

        TeamTitleInfo yzxx = new TeamTitleInfo();
        yzxx.setShowAdd(true);
        yzxx.setTitle("研制信息");
        NormalMultipleEntity titleYzxx = new NormalMultipleEntity(NormalMultipleEntity.TITLE,yzxx);
        list.add(titleYzxx);

        list.add(new NormalMultipleEntity(NormalMultipleEntity.YZ_INFO));

        list.add(new NormalMultipleEntity(NormalMultipleEntity.TITLE_FJ));
        list.add(new NormalMultipleEntity(NormalMultipleEntity.TITLE_FJ_IMG));
        list.add(new NormalMultipleEntity(NormalMultipleEntity.TITLE_FJ_IMG));
        list.add(new NormalMultipleEntity(NormalMultipleEntity.TITLE_FJ_IMG));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String format = simpleDateFormat.format(System.currentTimeMillis());
        FujianTimeTitleInfo fujianTimeTitleInfo = new FujianTimeTitleInfo();
        fujianTimeTitleInfo.setTitle("发布时间："+ format);
        NormalMultipleEntity fjsj = new NormalMultipleEntity(NormalMultipleEntity.TITLE_FJ_TIME,fujianTimeTitleInfo);
        list.add(fjsj);


        TeamDetailsAdapter teamDetailsAdapter = new TeamDetailsAdapter(list);
        teamDetailsAdapter.setGridSpanSizeLookup(new GridSpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int viewType, int position) {
                if (viewType == NormalMultipleEntity.TITLE_FJ_IMG) {
                    return 1;
                } else {
                    return 4;
                }
            }
        });

        teamDetailsAdapter.addItemProvider(new CjrInfoProvider());
        teamDetailsAdapter.addItemProvider(new TitleProvider());
        teamDetailsAdapter.addItemProvider(new LawCaseInfoProvider());
        teamDetailsAdapter.addItemProvider(new LawCaseDetailsProvider());
        CsInfoProvider csInfoProvider = new CsInfoProvider();
        teamDetailsAdapter.addItemProvider(csInfoProvider);
        FuJianImgProvider fuJianImgProvider = new FuJianImgProvider();
        TitleFjProvider titleFjProvider = new TitleFjProvider();
        TitleFjTimeProvider titleFjTimeProvider = new TitleFjTimeProvider();
        YanZhiInfoProvider yanZhiInfoProvider = new YanZhiInfoProvider();
        teamDetailsAdapter.addItemProvider(fuJianImgProvider);
        teamDetailsAdapter.addItemProvider(titleFjProvider);
        teamDetailsAdapter.addItemProvider(titleFjTimeProvider);
        teamDetailsAdapter.addItemProvider(yanZhiInfoProvider);


        mRv.setAdapter(teamDetailsAdapter);
    }
}
