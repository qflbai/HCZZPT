package com.yuanxin.hczzpt.time;

import android.app.Dialog;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.view.TimePickerView;
import com.yuanxin.hczzpt.App;
import com.yuanxin.hczzpt.R;

import java.text.SimpleDateFormat;

/**
 * @author: qflbai
 * @CreateDate: 2019/12/6 14:50
 * @Version: 1.0
 * @description:
 */
public class TimePickerUtil {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 获取年月日
     *
     * @param timePickerBuilder
     * @return
     */
    public static TimePickerView getYMDTimeTimePickerView(TimePickerBuilder timePickerBuilder) {
        return setTimePickerBOTTOM(timePickerBuilder
                .setType(new boolean[]{true, true, true, false, false, false})
                .isDialog(true) //默认设置false ，内部实现将DecorView 作为它的父控件。.setItemVisibleCount(10) //若设置偶数，实际值会加1（比如设置6，则最大可见条目为7）
                .setCancelColor(App.getAPPContext().getResources().getColor(R.color.colorAccent))
                .setSubmitColor(App.getAPPContext().getResources().getColor(R.color.colorAccent))
                .isAlphaGradient(true)
                .setItemVisibleCount(10) //若设置偶数，实际值会加1（比如设置6，则最大可见条目为7）
                .setLineSpacingMultiplier(2.0f)
                .isAlphaGradient(true)
                .build());

    }

    /**
     * 获取年月日时分
     *
     * @param timePickerBuilder
     * @return
     */
    public static TimePickerView getYMDHMTimeTimePickerView(TimePickerBuilder timePickerBuilder) {
        return setTimePickerBOTTOM(timePickerBuilder
                .setType(new boolean[]{true, true, true, true, true, false})
                .isDialog(true) //默认设置false ，内部实现将DecorView 作为它的父控件。.setItemVisibleCount(10) //若设置偶数，实际值会加1（比如设置6，则最大可见条目为7）
                .setCancelColor(App.getAPPContext().getResources().getColor(R.color.colorAccent))
                .setSubmitColor(App.getAPPContext().getResources().getColor(R.color.colorAccent))
                .isAlphaGradient(true)
                .setItemVisibleCount(10) //若设置偶数，实际值会加1（比如设置6，则最大可见条目为7）
                .setLineSpacingMultiplier(2.0f)
                .isAlphaGradient(true)
                .build());

    }

    /**
     * 获取年月日时分秒
     *
     * @param timePickerBuilder
     * @return
     */
    public static TimePickerView getYMDHMSTimeTimePickerView(TimePickerBuilder timePickerBuilder) {
        return setTimePickerBOTTOM(timePickerBuilder
                .setType(new boolean[]{true, true, true, true, true, true})
                .isDialog(true) //默认设置false ，内部实现将DecorView 作为它的父控件。.setItemVisibleCount(10) //若设置偶数，实际值会加1（比如设置6，则最大可见条目为7）
                .setCancelColor(App.getAPPContext().getResources().getColor(R.color.colorAccent))
                .setSubmitColor(App.getAPPContext().getResources().getColor(R.color.colorAccent))
                .isAlphaGradient(true)
                .setItemVisibleCount(10) //若设置偶数，实际值会加1（比如设置6，则最大可见条目为7）
                .setLineSpacingMultiplier(2.0f)
                .isAlphaGradient(true)
                .build());

    }


    /**
     * 获取时分秒
     *
     * @param timePickerBuilder
     * @return
     */
    public static TimePickerView getHMSTimeTimePickerView(TimePickerBuilder timePickerBuilder) {
        return setTimePickerBOTTOM(timePickerBuilder
                .setType(new boolean[]{false, false, false, true, true, true})
                .isDialog(true) //默认设置false ，内部实现将DecorView 作为它的父控件。.setItemVisibleCount(10) //若设置偶数，实际值会加1（比如设置6，则最大可见条目为7）
                .setCancelColor(App.getAPPContext().getResources().getColor(R.color.colorAccent))
                .setSubmitColor(App.getAPPContext().getResources().getColor(R.color.colorAccent))
                .isAlphaGradient(true)
                .setItemVisibleCount(10) //若设置偶数，实际值会加1（比如设置6，则最大可见条目为7）
                .setLineSpacingMultiplier(2.0f)
                .isAlphaGradient(true)
                .build());

    }

    public static TimePickerView setTimePickerBOTTOM(TimePickerView pvTime) {
        Dialog mDialog = pvTime.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
                dialogWindow.setDimAmount(0.3f);
            }
        }

        return pvTime;
    }
}
