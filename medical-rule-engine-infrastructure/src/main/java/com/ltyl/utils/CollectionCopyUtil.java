package com.ltyl.utils;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yucheng.yao
 */
public class CollectionCopyUtil {

    public static <T> List copyList(List<T> list, Class tClass) {
        if (list == null || list.isEmpty()) {
            return new ArrayList();
        }
        return JSON.parseArray(JSON.toJSONString(list), tClass);
    }

    public static Map<String, Object> copyMap(Map map) {
        return JSON.parseObject(JSON.toJSONString(map));
    }

}
