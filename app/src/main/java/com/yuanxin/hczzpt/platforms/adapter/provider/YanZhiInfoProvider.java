package com.yuanxin.hczzpt.platforms.adapter.provider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
public class YanZhiInfoProvider extends BaseItemProvider<NormalMultipleEntity> {
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_recycler_platforms_team_yz_info, null, false);
        return new YanZhiInfoProvider.Holder(inflate);
    }

    @Override
    public int getItemViewType() {
        return NormalMultipleEntity.YZ_INFO;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_recycler_platforms_team_yz_info;
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
