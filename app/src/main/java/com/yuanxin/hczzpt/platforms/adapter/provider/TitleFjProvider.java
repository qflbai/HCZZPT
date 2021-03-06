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
public class TitleFjProvider extends BaseItemProvider<NormalMultipleEntity> {

    @Override
    public int getItemViewType() {
        return NormalMultipleEntity.TITLE_FJ;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_recycler_platforms_team_title_fujian;
    }

    @Override
    public void convert(BaseViewHolder baseViewHolder, NormalMultipleEntity normalMultipleEntity) {
        Holder holder = (Holder) baseViewHolder;
        holder.tvTitle.setText("附件");
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_recycler_platforms_team_title_fujian, null, false);
        return new Holder(inflate);
    }

    public static class Holder extends BaseViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_xz)
        TextView tvXz;
        public Holder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
