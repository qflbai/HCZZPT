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
public class FuJianImgProvider extends BaseItemProvider<NormalMultipleEntity> {

    @Override
    public int getItemViewType() {
        return NormalMultipleEntity.TITLE_FJ_IMG;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_recycler_platforms_team_fu_img;
    }

    @Override
    public void convert(BaseViewHolder baseViewHolder, NormalMultipleEntity normalMultipleEntity) {
        AjNxInfo data = (AjNxInfo) normalMultipleEntity.content;
        Holder holder = (Holder) baseViewHolder;
        String title = data.getTitle();
        if (isBhImage(title)) {
            Glide.with(getContext()).load(data.getUrl())
                    .into(holder.iv);
        }


        holder.tvTitle.setText(data.getTitle());
    }

    public boolean isBhImage(String content) {
        if(content==null|| TextUtils.isEmpty(content)){
            return false;
        }
        if (content.contains(".png")) {
            return true;
        } else if (content.contains(".jpg")) {
            return true;
        } else if (content.contains(".bmp")) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_recycler_platforms_team_fu_img, null, false);
        return new Holder(inflate);
    }

    public static class Holder extends BaseViewHolder {
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv_title)
        TextView tvTitle;

        public Holder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
