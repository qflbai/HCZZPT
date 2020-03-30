package com.yuanxin.hczzpt.common.utils;

import com.qflbai.lib.utils.StringUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: qflbai
 * @CreateDate: 2019/12/24 11:42
 * @Version: 1.0
 * @description:
 */
public class CommonUtils {


    public static Map<String, Object> object2Map(Object obj) {
        Map<String, Object> map = new HashMap<>();
        if (obj == null) {
            return map;
        }
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(obj));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 去除null
     *
     * @param map
     * @return
     */
    public static Map<String, Object> removeNull(Map<String, Object> map) {
        Set<String> set = map.keySet();
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            String key = it.next();
            if (map.get(key) == null) {
                map.remove(key);
                set = map.keySet();
                it = set.iterator();
            }
        }

        return map;
    }

    /**
     * 添加逗号
     *
     * @param dataList
     */
    public static String dataAddDhString(List<String> dataList) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dataList.size(); i++) {
            if (sb.length() > 0) {//该步即不会第一位有逗号，也防止最后一位拼接逗号！
                sb.append(",");
            }
            sb.append(dataList.get(i));
        }

        return sb.toString();
    }

    /**
     * 添加分隔符
     * @param dataList
     */
    public static String dataAddSeparator(String separator, List<String> dataList) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dataList.size(); i++) {
            if (sb.length() > 0) {//该步即不会第一位有逗号，也防止最后一位拼接逗号！
                sb.append(separator);
            }
            sb.append(dataList.get(i));
        }
        return sb.toString();
    }

    /**
     * 解析斜杆json
     */
    public static String  parseXgJson(String xgJson) {
        return xgJson.replaceAll("\\\\", "");
    }
}
