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
import com.yuanxin.hczzpt.home.ui.fragment.ManagerFragment;

import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: qflbai
 * @CreateDate: 2020/1/8 20:00
 * @Version: 1.0
 * @description:
 */
public class CriminalSuspectAdapter extends RecyclerView.Adapter<CriminalSuspectAdapter.Holder> {
    private List<CriminalSuspectInfo> data;
    private Context context;
    private OnItemClick mOnItemClick;
    private OnItemClick mOnBjClick;

    public CriminalSuspectAdapter(Context context, List<CriminalSuspectInfo> data) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_recycler_criminal_suspect, parent, false);
        return new Holder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
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

        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
