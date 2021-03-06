package com.yuanxin.hczzpt.platforms.adapter.provider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yuanxin.hczzpt.R;
import com.yuanxin.hczzpt.platforms.adapter.NormalMultipleEntity;
import com.yuanxin.hczzpt.platforms.bean.FujianTimeTitleInfo;
import com.yuanxin.hczzpt.platforms.bean.TeamTitleInfo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: qflbai
 * @CreateDate: 2020/1/9 16:27
 * @Version: 1.0
 * @description:
 */
public class TitleFjTimeProvider extends BaseItemProvider<NormalMultipleEntity> {

    private OnItemClick mXz;

    @Override
    public int getItemViewType() {
        return NormalMultipleEntity.TITLE_FJ_TIME;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_recycler_platforms_team_title_fujian_time;
    }

    @Override
    public void convert(BaseViewHolder baseViewHolder, NormalMultipleEntity normalMultipleEntity) {
        Holder holder = (Holder) baseViewHolder;
        FujianTimeTitleInfo content = (FujianTimeTitleInfo) normalMultipleEntity.content;
        if(content==null){
            return;
        }
        holder.tvTitle.setText(content.getTitle());

        if(content.isShowAdd()){
            holder.tvXz.setVisibility(View.VISIBLE);
            holder.tvXz.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mXz!=null){
                        mXz.onItemClick(v,content);
                    }
                }
            });
        }else {
            holder.tvXz.setVisibility(View.GONE);
        }


    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_recycler_platforms_team_title_fujian_time, null, false);
        return new Holder(inflate);
    }

    public void setOnXz(OnItemClick onXz){
        mXz = onXz;
    }

    public interface OnItemClick {
        void onItemClick(View view,  FujianTimeTitleInfo teamTitleInfo);
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
