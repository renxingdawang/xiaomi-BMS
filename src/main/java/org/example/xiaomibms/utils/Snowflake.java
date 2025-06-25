package org.example.xiaomibms.utils;

import cn.hutool.core.util.IdUtil;

public class Snowflake {
    public static String SnowflakeUid(){
        cn.hutool.core.lang.Snowflake snowflake= IdUtil.getSnowflake(1,1);
        String fullId=snowflake.nextIdStr();
        return fullId.substring(0,16);
    }
}
