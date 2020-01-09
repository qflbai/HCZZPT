package com.yuanxin.hczzpt.platforms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.qflbai.lib.base.activity.BaseActivity;
import com.yuanxin.hczzpt.R;
import com.yuanxin.hczzpt.platforms.adapter.NormalMultipleEntity;
import com.yuanxin.hczzpt.platforms.adapter.TeamDetailsAdapter;
import com.yuanxin.hczzpt.platforms.adapter.provider.CjrInfoProvider;
import com.yuanxin.hczzpt.platforms.adapter.provider.CsInfoProvider;
import com.yuanxin.hczzpt.platforms.adapter.provider.LawCaseDetailsProvider;
import com.yuanxin.hczzpt.platforms.adapter.provider.LawCaseInfoProvider;
import com.yuanxin.hczzpt.platforms.adapter.provider.TitleProvider;

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


    private void initRv(){
        GridLayoutManager manager = new GridLayoutManager(mContext, 1);
        mRv.setLayoutManager(manager);

        List<NormalMultipleEntity> list = new ArrayList<>();
        for (int i = 0; i <= 4; i++) {
            list.add(new NormalMultipleEntity(NormalMultipleEntity.TITLE));
            list.add(new NormalMultipleEntity(NormalMultipleEntity.LAW_CASE_DETAILS_ITEM));
            list.add(new NormalMultipleEntity(NormalMultipleEntity.TITLE));
            list.add(new NormalMultipleEntity(NormalMultipleEntity.CJR_INFO));
            list.add(new NormalMultipleEntity(NormalMultipleEntity.TITLE));
            list.add(new NormalMultipleEntity(NormalMultipleEntity.LAW_CASE_INFO));
            list.add(new NormalMultipleEntity(NormalMultipleEntity.XYR_INFO));
        }

        TeamDetailsAdapter teamDetailsAdapter = new TeamDetailsAdapter(list);
        teamDetailsAdapter.addItemProvider(new CjrInfoProvider());
        teamDetailsAdapter.addItemProvider(new TitleProvider());
        teamDetailsAdapter.addItemProvider(new LawCaseInfoProvider());
        teamDetailsAdapter.addItemProvider(new LawCaseDetailsProvider());
        CsInfoProvider csInfoProvider = new CsInfoProvider();
        teamDetailsAdapter.addItemProvider(csInfoProvider);
        mRv.setAdapter(teamDetailsAdapter);
    }
}
