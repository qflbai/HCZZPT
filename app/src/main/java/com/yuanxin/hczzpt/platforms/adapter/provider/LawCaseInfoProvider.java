package com.yuanxin.hczzpt.platforms.adapter.provider;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yuanxin.hczzpt.R;
import com.yuanxin.hczzpt.platforms.adapter.NormalMultipleEntity;
import com.yuanxin.hczzpt.platforms.bean.AjNxInfo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: qflbai
 * @CreateDate: 2020/1/9 16:27
 * @Version: 1.0
 * @description:
 */
public class LawCaseInfoProvider extends BaseItemProvider<NormalMultipleEntity> {
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_recycler_platforms_team_lc_info, null, false);
        return new LawCaseInfoProvider.Holder(inflate);
    }
    @Override
    public int getItemViewType() {
        return NormalMultipleEntity.LAW_CASE_INFO;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_recycler_platforms_team_lc_info;
    }

    @Override
    public void convert(BaseViewHolder baseViewHolder, NormalMultipleEntity normalMultipleEntity) {
        AjNxInfo data = (AjNxInfo) normalMultipleEntity.content;
        Holder holder = (Holder) baseViewHolder;

        String title = data.getTitle();
        String url = data.getUrl();
        if(title==null|| TextUtils.isEmpty(title)){
            holder.tvTitle.setVisibility(View.GONE);
        }else {
            holder.tvTitle.setVisibility(View.VISIBLE);
            holder.tvTitle.setText(title
            );
        }

        if(url==null|| TextUtils.isEmpty(url)){
            holder.ivImage.setVisibility(View.INVISIBLE);
        }else {
            holder.ivImage.setVisibility(View.VISIBLE);

            Glide.with(getContext()).load(url).into(holder.ivImage);
        }
    }


    public static class Holder extends BaseViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.iv_image)
        ImageView ivImage;
        public Holder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
