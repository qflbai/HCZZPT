package com.yuanxin.hczzpt.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yuanxin.hczzpt.R;
import com.yuanxin.hczzpt.home.bean.CriminalSuspectInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: qflbai
 * @CreateDate: 2020/1/8 20:00
 * @Version: 1.0
 * @description:
 */
public class CriminalSuspectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<CriminalSuspectInfo> data;
    private Context context;
    private OnItemClick mOnItemClick;
    private OnItemClick mOnBjClick;
    private OnItemClick mOnDeleteClick;


    public CriminalSuspectAdapter(Context context, List<CriminalSuspectInfo> data) {
        this.data = data;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_recycler_criminal_suspect, parent, false);
        return new Holder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        Holder holder = (Holder) viewHolder;
        holder.tvSee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClick != null) {
                    mOnItemClick.onItemClick(v, position);
                }
            }
        });

        holder.tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnBjClick != null) {
                    mOnBjClick.onItemClick(v, position);
                }
            }
        });

        holder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnDeleteClick != null) {
                    mOnDeleteClick.onItemClick(v, position);
                }
            }
        });

    }

    public void setOnDeleteClick(OnItemClick onItemClick) {
        mOnDeleteClick = onItemClick;
    }

    public void setOnBjClick(OnItemClick onItemClick) {
        mOnBjClick = onItemClick;
    }

    public void setOnCkXqClick(OnItemClick onItemClick) {
        mOnItemClick = onItemClick;
    }

    public interface OnItemClick {
        void onItemClick(View view, int position);
    }

    @Override
    public int getItemCount() {
        return data.size() <= 0 ? 100 : data.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_see)
        TextView tvSee;
        @BindView(R.id.tv_edit)
        TextView tvEdit;
        @BindView(R.id.tv_delete)
        TextView tvDelete;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_wxh)
        TextView tvWxh;
        @BindView(R.id.tv_cardid)
        TextView tvCardid;
        @BindView(R.id.criminal_id)
        TextView criminalId;
        @BindView(R.id.tv_gzr)
        TextView tvGzr;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
