package com.yuanxin.hczzpt.platforms.adapter.provider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yuanxin.hczzpt.R;
import com.yuanxin.hczzpt.platforms.adapter.NormalMultipleEntity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: qflbai
 * @CreateDate: 2020/1/9 16:27
 * @Version: 1.0
 * @description:
 */
public class CsInfoProvider extends BaseItemProvider<NormalMultipleEntity> {
    // 嫌疑人信息
    @Override
    public int getItemViewType() {
        return NormalMultipleEntity.XYR_INFO;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_recycler_platforms_team_xyr_info;
    }

    @Override
    public void convert(BaseViewHolder baseViewHolder, NormalMultipleEntity normalMultipleEntity) {
       // Holder holder = (Holder) baseViewHolder;
        //holder.tvTitle.setText("标题");
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_recycler_platforms_team_xyr_info, null, false);
        return new Holder(inflate);
    }

    public static class Holder extends BaseViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        public Holder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
