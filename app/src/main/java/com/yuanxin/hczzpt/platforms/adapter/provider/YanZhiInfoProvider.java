package com.yuanxin.hczzpt.platforms.adapter.provider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yuanxin.hczzpt.R;
import com.yuanxin.hczzpt.constant.NetApi;
import com.yuanxin.hczzpt.platforms.adapter.NormalMultipleEntity;
import com.yuanxin.hczzpt.platforms.bean.TeamDetailsInfo;

import butterknife.BindView;
import butterknife.ButterKnife;

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
        TeamDetailsInfo.DataBean.HlistBean data = (TeamDetailsInfo.DataBean.HlistBean) normalMultipleEntity.content;

        Holder holder = (Holder) baseViewHolder;
        Glide.with(getContext()).load(NetApi.baseUrl+data.getAvatar_id())
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(holder.ivTx);

        holder.tvName.setText(data.getUser_id());
        holder.tvZw.setText(data.getUser_role());
        holder.tvContent.setText(data.getSummary());
        holder.tvJh.setText(data.getSystem_id());
    }


    public static class Holder extends BaseViewHolder {
        @BindView(R.id.iv_tx)
        ImageView ivTx;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_jh)
        TextView tvJh;
        @BindView(R.id.tv_zw)
        TextView tvZw;
        @BindView(R.id.tv_content)
        TextView tvContent;

        public Holder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
