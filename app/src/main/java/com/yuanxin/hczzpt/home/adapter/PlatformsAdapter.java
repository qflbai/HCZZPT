package com.yuanxin.hczzpt.home.adapter;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.yuanxin.hczzpt.R;
import com.yuanxin.hczzpt.home.bean.AjInfo;
import com.yuanxin.hczzpt.home.bean.CriminalSuspectInfo;
import com.yuanxin.hczzpt.time.TimePickerUtil;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.NiceSpinnerPro;
import org.angmarch.views.OnSpinnerItemSelectedListener;
import org.angmarch.views.SimpleSpinnerTextFormatter;
import org.angmarch.views.SpinnerTextFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: qflbai
 * @CreateDate: 2020/1/8 20:00
 * @Version: 1.0
 * @description:
 */
public class PlatformsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<AjInfo.DataBean.ListBean> data;
    private Context context;
    private OnItemClick mOnItemClick;
    private OnItemClick mOnBjClick;
    public static final int view_type_0 = 0;
    public static final int view_type_1 = 1;
    private CriminalSuspectAdapter.OnItemClick mOnDeleteClick;
    private OnItemSelectClick mOnAjXz;
    private OnItemSelectClick onTimeClick;
    private OnItemSelectClick onItemFlBq;

    public PlatformsAdapter(Context context, List<AjInfo.DataBean.ListBean> data) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return view_type_0;
        }
        return view_type_1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == view_type_1) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_recycler_platforms_1, parent, false);
            return new Holder(inflate);
        } else {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_recycler_platforms_head, parent, false);
            return new HeaderHolder(inflate);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder.getItemViewType() == view_type_1) {
            Holder holder = (Holder) viewHolder;
            holder.root.setOnClickListener(new View.OnClickListener() {
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

            AjInfo.DataBean.ListBean listBean = data.get(position - 1);

            holder.tvName.setText(listBean.getCase_name());
            holder.tvSjh.setText(listBean.getCreated_man());
            holder.tvCardid.setText(listBean.getReported_time());
            String case_state = listBean.getCase_state();
            if("10".equals(case_state)){
                case_state = "进行中";
            }else if("11".equals(case_state)){
                case_state = "行动中";
            }else if("20".equals(case_state)){
                case_state = "结案";
            }
            holder.tvState.setText(case_state);
            holder.tvXzfl.setText(listBean.getCase_type());
            holder.tvTime.setText(listBean.getCreated_time() + " 创建");
        } else {
            HeaderHolder headerHolder = (HeaderHolder) viewHolder;
            headerHolder.ivTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ivTime(headerHolder.tvTime);
                }
            });

            initSpinner(headerHolder.mSpinner);

            headerHolder.tvTypeDx.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setBg(true, headerHolder.tvTypeDx);
                    setBg(false, headerHolder.tvTypeSd);
                    setBg(false, headerHolder.tvWw);
                    setBg(false, headerHolder.tvTypeXyr);
                    setBg(false, headerHolder.tvTypeQt);

                    onItemFlBq.onItemClick("dui");
                }
            });

            headerHolder.tvTypeSd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setBg(true, headerHolder.tvTypeSd);
                    setBg(false, headerHolder.tvTypeDx);
                    setBg(false, headerHolder.tvWw);
                    setBg(false, headerHolder.tvTypeXyr);
                    setBg(false, headerHolder.tvTypeQt);
                    onItemFlBq.onItemClick("du");
                }
            });

            headerHolder.tvWw.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemFlBq.onItemClick("wen");

                    setBg(false, headerHolder.tvTypeSd);
                    setBg(false, headerHolder.tvTypeDx);
                    setBg(true, headerHolder.tvWw);
                    setBg(false, headerHolder.tvTypeXyr);
                    setBg(false, headerHolder.tvTypeQt);
                }
            });

            headerHolder.tvTypeXyr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemFlBq.onItemClick("man");

                    setBg(false, headerHolder.tvTypeSd);
                    setBg(false, headerHolder.tvTypeDx);
                    setBg(false, headerHolder.tvWw);
                    setBg(true, headerHolder.tvTypeXyr);
                    setBg(false, headerHolder.tvTypeQt);
                }
            });

            headerHolder.tvTypeQt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemFlBq.onItemClick("otd");
                    setBg(false, headerHolder.tvTypeSd);
                    setBg(false, headerHolder.tvTypeDx);
                    setBg(false, headerHolder.tvWw);
                    setBg(false, headerHolder.tvTypeXyr);
                    setBg(true, headerHolder.tvTypeQt);
                }
            });
        }
    }

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public String mTime;

    public void ivTime(TextView tvTime) {
        TimePickerBuilder alphaGradient = new TimePickerBuilder(context, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                mTime = simpleDateFormat.format(date);
                tvTime.setText(mTime);
                if (onTimeClick != null) {
                    onTimeClick.onItemClick(mTime);
                }
            }
        });


        //时间选择器
        TimePickerView pvTime = TimePickerUtil.getYMDTimeTimePickerView(alphaGradient);
        pvTime.show();
    }

    private void setBg(boolean isSelect, TextView view) {
        if (isSelect) {
            view.setTextColor(context.getResources().getColor(R.color.white));
            view.setBackground(context.getResources().getDrawable(R.drawable.shape_corners_stroke_coloraccent));
        } else {
            view.setTextColor(context.getResources().getColor(R.color.colorAccent));
            view.setBackground(context.getResources().getDrawable(R.drawable.shape_corners_stroke_white));
        }
    }

    public String spinerSlelet;
    List<AjInfo.DataBean.CaseTypeBean> dataset = new ArrayList<>();

    public void setSpinnerData(List<AjInfo.DataBean.CaseTypeBean> data) {
        dataset.clear();
        dataset.addAll(data);
    }

    private void initSpinner(NiceSpinnerPro niceSpinner) {
        niceSpinner.setSelectedTextFormatter(new SimpleSpinnerTextFormatter(){
            @Override
            public Spannable format(Object item) {
                AjInfo.DataBean.CaseTypeBean item1 = (AjInfo.DataBean.CaseTypeBean) item;
                return new SpannableString(item1.getName()) ;
            }
        });
        niceSpinner.setSpinnerTextFormatter(new SpinnerTextFormatter() {
            @Override
            public Spannable format(Object o) {
                AjInfo.DataBean.CaseTypeBean item1 = (AjInfo.DataBean.CaseTypeBean) o;
                return new SpannableString(item1.getName()) ;
            }
        });
        niceSpinner.attachDataSource(dataset);
        niceSpinner.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {

            }

            @Override
            public void onItemSelected(View view, int i, long l) {
                AjInfo.DataBean.CaseTypeBean caseTypeBean = dataset.get(i);
                spinerSlelet = caseTypeBean.getName();
                if (mOnAjXz != null) {
                    mOnAjXz.onItemClick(caseTypeBean.getId()+"");
                }
            }
        });

        if (spinerSlelet != null) {
            niceSpinner.setText(spinerSlelet);
        }
    }

    public void setOnItemFlBq(OnItemSelectClick selectClick){
        onItemFlBq = selectClick;
    }

    public void setOnDeleteClick(CriminalSuspectAdapter.OnItemClick onItemClick) {
        mOnDeleteClick = onItemClick;
    }

    public void setOnTimeClick(OnItemSelectClick onItemClick) {
        onTimeClick = onItemClick;
    }

    public void setOnAjXzClick(OnItemSelectClick onItemClick) {
        mOnAjXz = onItemClick;
    }

    public void setOnBjClick(OnItemClick onItemClick) {
        mOnBjClick = onItemClick;
    }


    public void setOnItemClick(OnItemClick onItemClick) {
        mOnItemClick = onItemClick;
    }

    public interface OnItemClick {
        void onItemClick(View view, int position);
    }

    public interface OnItemSelectClick {
        void onItemClick(String value);
    }

    @Override
    public int getItemCount() {
        return data.size() + 1;
    }

    public static class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.root)
        ConstraintLayout root;
        @BindView(R.id.tv_edit)
        TextView tvEdit;
        @BindView(R.id.tv_delete)
        TextView tvDelete;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_cph)
        TextView tvCph;
        @BindView(R.id.tv_sjh)
        TextView tvSjh;
        @BindView(R.id.tv_cardid)
        TextView tvCardid;
        @BindView(R.id.tv_state)
        TextView tvState;
        @BindView(R.id.tv_xzfl)
        TextView tvXzfl;
        @BindView(R.id.tv_time)
        TextView tvTime;


        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class HeaderHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.nice_xz)
        NiceSpinnerPro mSpinner;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_type_dx)
        TextView tvTypeDx;
        @BindView(R.id.tv_type_sd)
        TextView tvTypeSd;
        @BindView(R.id.iv_time)
        ImageView ivTime;
        @BindView(R.id.tv_type_ww)
        TextView tvWw;
        @BindView(R.id.tv_type_xyr)
        TextView tvTypeXyr;
        @BindView(R.id.tv_type_xt)
        TextView tvTypeQt;

        public HeaderHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
