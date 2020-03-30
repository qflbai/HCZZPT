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
public class CjrInfoProvider extends BaseItemProvider<NormalMultipleEntity> {
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_recycler_platforms_team_cjr_info, null, false);
        return new CjrInfoProvider.Holder(inflate);
    }
    //创建人信息
    @Override
    public int getItemViewType() {
        return NormalMultipleEntity.CJR_INFO;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_recycler_platforms_team_cjr_info;
    }

    @Override
    public void convert(BaseViewHolder baseViewHolder, NormalMultipleEntity normalMultipleEntity) {
        TeamDetailsInfo.DataBean.CaseBean content = (TeamDetailsInfo.DataBean.CaseBean) normalMultipleEntity.content;

        Holder holder = (Holder) baseViewHolder;
        holder.tvJh.setText(content.getSystem_id());
        holder.tvUnit.setText(content.getCreated_role());
        holder.tvNmae.setText(content.getCreated_man());
    }



    public static class Holder extends BaseViewHolder {
        @BindView(R.id.tv_name)
        TextView tvNmae;
        @BindView(R.id.tv_unit)
        TextView tvUnit;
        @BindView(R.id.tv_jh)
        TextView tvJh;
        public Holder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
