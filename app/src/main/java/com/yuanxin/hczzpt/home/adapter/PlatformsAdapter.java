package com.yuanxin.hczzpt.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
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
public class PlatformsAdapter extends RecyclerView.Adapter<PlatformsAdapter.Holder> {
    private List<CriminalSuspectInfo> data;
    private Context context;
    private OnItemClick mOnItemClick;
    private OnItemClick mOnBjClick;

    public PlatformsAdapter(Context context, List<CriminalSuspectInfo> data) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_recycler_platforms, parent, false);
        return new Holder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnItemClick!=null){
                    mOnItemClick.onItemClick(v,position);
                }
            }
        });

        holder.tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnBjClick!=null){
                    mOnBjClick.onItemClick(v,position);
                }
            }
        });
    }

    public void setOnBjClick(OnItemClick onItemClick){
        mOnBjClick = onItemClick;
    }


    public void setOnItemClick(OnItemClick onItemClick){
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
        @BindView(R.id.root)
        ConstraintLayout root;
        @BindView(R.id.tv_edit)
        TextView tvEdit;
        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
