package com.yuanxin.hczzpt.platforms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.chad.library.adapter.base.listener.GridSpanSizeLookup;
import com.qflbai.lib.base.activity.BaseActivity;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TeamDetailsActivity extends BaseActivity {
    @BindView(R.id.rv)
    RecyclerView mRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_details);
        initRv();
    }


    private void initRv() {
        GridLayoutManager manager = new GridLayoutManager(mContext, 4);
        mRv.setLayoutManager(manager);


        List<NormalMultipleEntity> list = new ArrayList<>();

        list.add(new NormalMultipleEntity(NormalMultipleEntity.TITLE));
        list.add(new NormalMultipleEntity(NormalMultipleEntity.LAW_CASE_DETAILS_ITEM));
        list.add(new NormalMultipleEntity(NormalMultipleEntity.TITLE));
        list.add(new NormalMultipleEntity(NormalMultipleEntity.CJR_INFO));
        list.add(new NormalMultipleEntity(NormalMultipleEntity.TITLE));
        list.add(new NormalMultipleEntity(NormalMultipleEntity.LAW_CASE_INFO));
        list.add(new NormalMultipleEntity(NormalMultipleEntity.TITLE));
        list.add(new NormalMultipleEntity(NormalMultipleEntity.XYR_INFO));
        list.add(new NormalMultipleEntity(NormalMultipleEntity.TITLE));
        list.add(new NormalMultipleEntity(NormalMultipleEntity.YZ_INFO));
        list.add(new NormalMultipleEntity(NormalMultipleEntity.TITLE_FJ));
        list.add(new NormalMultipleEntity(NormalMultipleEntity.TITLE_FJ_IMG));
        list.add(new NormalMultipleEntity(NormalMultipleEntity.TITLE_FJ_IMG));
        list.add(new NormalMultipleEntity(NormalMultipleEntity.TITLE_FJ_IMG));
        list.add(new NormalMultipleEntity(NormalMultipleEntity.TITLE_FJ_TIME));


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
