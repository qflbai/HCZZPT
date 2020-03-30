package com.yuanxin.hczzpt.home.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yuanxin.hczzpt.R;
import com.yuanxin.hczzpt.home.bean.CriminalSuspectDetailsInfo;
import com.yuanxin.hczzpt.home.bean.CriminalSuspectInfo;
import com.yuanxin.hczzpt.home.bean.ZpjlTpInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: qflbai
 * @CreateDate: 2020/1/8 20:00
 * @Version: 1.0
 * @description:
 */
public class ImgAdapter extends RecyclerView.Adapter<ImgAdapter.Holder> {
    private List<ZpjlTpInfo> data;
    private Context context;

    public ImgAdapter(Context context, List<ZpjlTpInfo> data) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_recycler_platforms_team_lc_info_new, parent, false);
        return new Holder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.tvTitle.setVisibility(View.GONE);
        ZpjlTpInfo zpjlTpInfo = data.get(position);

        CriminalSuspectDetailsInfo.ExtBean extBean = zpjlTpInfo.getExtBean();
        String iamgeUrl1 = zpjlTpInfo.getIamgeUrl();
        holder.tvTitle.setVisibility(View.GONE);
        if (extBean != null) {
            holder.mll.setVisibility(View.VISIBLE);
            holder.ivImage.setVisibility(View.GONE);
            holder.mTvDz.setText(extBean.getAction_address());
            holder.mTvAjmc.setText(extBean.getAction_name());
            holder.mTvZpsj.setText(extBean.getAction_time());
            holder.mTvAjxz.setText(extBean.getAction_type());
        } else {
            holder.mll.setVisibility(View.GONE);
            if (iamgeUrl1 != null) {
                holder.ivImage.setVisibility(View.VISIBLE);
                String iamgeUrl = zpjlTpInfo.getIamgeUrl();
                Glide.with(context).load(iamgeUrl).into(holder.ivImage);

            } else {
                holder.ivImage.setVisibility(View.GONE);
            }
        }


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.iv_image)
        ImageView ivImage;
        @BindView(R.id.tv_dz)
        TextView mTvDz;
        @BindView(R.id.tv_ajmc)
        TextView mTvAjmc;
        @BindView(R.id.tv_cpsj)
        TextView mTvZpsj;
        @BindView(R.id.tv_ajxz)
        TextView mTvAjxz;
        @BindView(R.id.ll_3)
        LinearLayout mll;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
