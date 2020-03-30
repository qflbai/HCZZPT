package com.yuanxin.hczzpt.time;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.yuanxin.hczzpt.App;
import com.yuanxin.hczzpt.R;
import com.yuanxin.hczzpt.platforms.bean.AjXzInfo;

public class SelectPickerUtil {
    public static OptionsPickerBuilder getSelectPickerBuilder(OptionsPickerBuilder optionsPickerBuilder) {
        optionsPickerBuilder
                .setCancelColor(App.getAPPContext().getResources().getColor(R.color.colorAccent))
                .setSubmitColor(App.getAPPContext().getResources().getColor(R.color.colorAccent))
                .isAlphaGradient(true)
                .setItemVisibleCount(10) //若设置偶数，实际值会加1（比如设置6，则最大可见条目为7）
                .setLineSpacingMultiplier(2.0f);
        return optionsPickerBuilder;
    }
}
