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
public class CsInfoProvider extends BaseItemProvider<NormalMultipleEntity> {

    private OnItemClick mDeleteClick;
    private OnItemClick mEditCkick;

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
        TeamDetailsInfo.DataBean.MlistBean data = (TeamDetailsInfo.DataBean.MlistBean) normalMultipleEntity.content;
        Holder holder = (Holder) baseViewHolder;
        holder.tvName.setText(data.getMan_name());
        holder.tvCardid.setText(data.getId_card());
        holder.tvWxh.setText(data.getId_wechat());
        holder.tvXyrid.setText(data.getId());

        holder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDeleteClick!=null){
                    mDeleteClick.onItemClick(v,data);
                }
            }
        });

        holder.tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mEditCkick!=null){
                    mEditCkick.onItemClick(v,data);
                }
            }
        });


        String tag = data.getTag();
        if ("wen".equals(tag)) {
            tag = "维稳人员";
        } else if ("du".equals(tag)) {
            tag = "涉毒人员";
        } else if ("dui".equals(tag)) {
            tag = "对象人员";
        } else if ("man".equals(tag)) {
            tag = "嫌疑人";
        } else if ("otd".equals(tag)) {
            tag = "其他";
        }

        holder.tvFl.setText(tag);

    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_recycler_platforms_team_xyr_info, null, false);
        return new Holder(inflate);
    }

    public void setOnEditClick(OnItemClick onEdit){
        mEditCkick = onEdit;
    }

    public void setDeleteClick(OnItemClick deleteClick){
        mDeleteClick = deleteClick;
    }

    public interface OnItemClick {
        void onItemClick(View view,  TeamDetailsInfo.DataBean.MlistBean mlistBean);
    }

    public static class Holder extends BaseViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_wxh)
        TextView tvWxh;
        @BindView(R.id.tv_cardid)
        TextView tvCardid;
        @BindView(R.id.criminal_id)
        TextView tvXyrid;
        @BindView(R.id.tv_fl)
        TextView tvFl;
        @BindView(R.id.tv_delete)
        TextView tvDelete;
        @BindView(R.id.tv_edit)
        TextView tvEdit;
        public Holder(View view) {
            super(view);
           ButterKnife.bind(this, view);
        }
    }
}
