package com.yuanxin.hczzpt.platforms.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseProviderMultiAdapter;
import com.yuanxin.hczzpt.platforms.adapter.provider.CjrInfoProvider;
import com.yuanxin.hczzpt.platforms.adapter.provider.CsInfoProvider;
import com.yuanxin.hczzpt.platforms.adapter.provider.LawCaseDetailsProvider;
import com.yuanxin.hczzpt.platforms.adapter.provider.LawCaseInfoProvider;
import com.yuanxin.hczzpt.platforms.adapter.provider.TitleProvider;

import java.util.List;

/**
 * @author: qflbai
 * @CreateDate: 2020/1/9 16:08
 * @Version: 1.0
 * @description:
 */
public class TeamDetailsAdapter extends BaseProviderMultiAdapter<NormalMultipleEntity> {

    public TeamDetailsAdapter(@Nullable List<NormalMultipleEntity> data) {
        super(data);

    }


    @Override
    protected int getItemType(List<? extends NormalMultipleEntity> list, int i) {
        return list.get(i).type;
    }
}
