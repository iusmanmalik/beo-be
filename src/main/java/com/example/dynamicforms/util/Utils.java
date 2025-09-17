package com.example.dynamicforms.util;

import java.util.List;

public class Utils {
    public static boolean isNullOrEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }
    public static boolean isNotNullOrEmpty(List<?> list) {
        return !isNullOrEmpty(list);
    }
}
