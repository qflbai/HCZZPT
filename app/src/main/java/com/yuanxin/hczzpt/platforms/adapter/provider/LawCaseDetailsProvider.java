package com.yuanxin.hczzpt.platforms.adapter.provider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yuanxin.hczzpt.R;
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
public class LawCaseDetailsProvider extends BaseItemProvider<NormalMultipleEntity> {
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_recycler_platforms_team_lc_dp, null, false);
        return new LawCaseDetailsProvider.Holder(inflate);
    }
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
        TeamDetailsInfo.DataBean.CaseBean data = (TeamDetailsInfo.DataBean.CaseBean) normalMultipleEntity.content;
        Holder holder = (Holder) baseViewHolder;
        holder.tvAjid.setText(data.getId());
        holder.tvAjmc.setText(data.getCase_name());
        holder.tvAfsj.setText(data.getHappened_time());
        holder.tvBasj.setText(data.getReported_time());
        holder.tvAjxz.setText(data.getCase_type());

        String case_state = data.getCase_state();
        if("10".equals(case_state)){
            case_state = "进行中";
        }else if("11".equals(case_state)){
            case_state = "行动中";
        }else if("20".equals(case_state)){
            case_state = "结案";
        }
        holder.tvAjzt.setText(case_state);
    }

    public static class Holder extends BaseViewHolder {
        @BindView(R.id.tv_aj_id)
        TextView tvAjid;
        @BindView(R.id.tv_ajmc)
        TextView tvAjmc;
        @BindView(R.id.tv_afsj)
        TextView tvAfsj;
        @BindView(R.id.tv_bnsj)
        TextView tvBasj;
        @BindView(R.id.tv_ajxz)
        TextView tvAjxz;
        @BindView(R.id.tv_ajzt)
        TextView tvAjzt;
        public Holder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
