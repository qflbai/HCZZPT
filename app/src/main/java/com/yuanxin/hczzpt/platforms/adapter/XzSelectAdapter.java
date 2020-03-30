package com.yuanxin.hczzpt.platforms.adapter;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.qflbai.lib.utils.toast.ToastUtil;
import com.yuanxin.hczzpt.R;
import com.yuanxin.hczzpt.platforms.bean.BmRyInfo;
import com.yuanxin.hczzpt.platforms.bean.XzSelectInfo;
import com.yuanxin.hczzpt.platforms.ui.activity.XzSelectActivity;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.NiceSpinnerPro;
import org.angmarch.views.OnSpinnerItemSelectedListener;
import org.angmarch.views.SimpleSpinnerTextFormatter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class XzSelectAdapter extends RecyclerView.Adapter<XzSelectAdapter.Holder> {

    private final List<XzSelectInfo> mData;
    private final Context mContext;

    public XzSelectAdapter(Context context, List<XzSelectInfo> data) {
        mContext = context;
        mData = data;
    }

    @NonNull
    @Override
    public XzSelectAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(mContext).inflate(R.layout.recyclerview_item_xz_selsect, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull XzSelectAdapter.Holder holder, int position) {
        XzSelectInfo xzSelectInfo = mData.get(position);
        String name = xzSelectInfo.getName();
        holder.tvName.setText(name);

        holder.cb.setChecked(xzSelectInfo.isSelect());
        holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked&&xzSelectInfo.getSelectType()==null){
                    ToastUtil.showCenter(mContext,"请先选择协助类型");
                    holder.cb.setChecked(false);
                    return;
                }
                xzSelectInfo.setSelect(isChecked);
                holder.cb.setChecked(isChecked);
            }
        });



        NiceSpinnerPro niceSpinner = holder.niceSpinner;

        List<BmRyInfo.TypeBean> typeBeans = xzSelectInfo.getTypeBeans();
        if (typeBeans == null) {
            return;
        }

        niceSpinner.setSpinnerTextFormatter(new SimpleSpinnerTextFormatter() {
            @Override
            public Spannable format(Object item) {
                BmRyInfo.TypeBean item1 = (BmRyInfo.TypeBean) item;
                return new SpannableString(item1.getName());
            }
        });
        niceSpinner.setSelectedTextFormatter(new SimpleSpinnerTextFormatter(){
            @Override
            public Spannable format(Object item) {
                BmRyInfo.TypeBean item1 = (BmRyInfo.TypeBean) item;
                return new SpannableString(item1.getName());
            }
        });
        niceSpinner.attachDataSource(typeBeans);


        niceSpinner.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {

            }

            @Override
            public void onItemSelected(View view, int i, long l) {
                BmRyInfo.TypeBean typeBean = typeBeans.get(i);
                xzSelectInfo.setSelectText(typeBean.getName());
                xzSelectInfo.setSelectType(typeBean);
            }
        });

        String selectText = xzSelectInfo.getSelectText();
        if (selectText != null) {
            niceSpinner.setText(selectText);
        }
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.select_checkbox)
        CheckBox cb;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.np_xz)
        NiceSpinnerPro niceSpinner;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
