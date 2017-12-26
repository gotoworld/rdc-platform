package com.hsd.devops.core.util;

import com.baomidou.mybatisplus.toolkit.IdWorker;


public class IdGenerator {

    public static String getId() {
        return String.valueOf(IdWorker.getId());
    }

    public static long getIdLong() {
        return IdWorker.getId();
    }
}
