package com.yuanxin.hczzpt.platforms.adapter.provider;

import android.view.View;

import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yuanxin.hczzpt.R;
import com.yuanxin.hczzpt.platforms.adapter.NormalMultipleEntity;

/**
 * @author: qflbai
 * @CreateDate: 2020/1/9 16:27
 * @Version: 1.0
 * @description:
 */
public class LawCaseDetailsProvider extends BaseItemProvider<NormalMultipleEntity> {
    //案件详情列表

    @Override
    public int getItemViewType() {
        return NormalMultipleEntity.LAW_CASE_DETAILS_ITEM;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_recycler_platforms_team_lc_dp;
    }

    @Override
    public void convert(BaseViewHolder baseViewHolder, NormalMultipleEntity normalMultipleEntity) {

    }

    public static class Holder extends BaseViewHolder {

        public Holder(View view) {
            super(view);
        }
    }
}
