package com.dragon.test.netty.utils;

/**
 * @ClassName StringUtils
 * @Description TODO
 * @SysUser Administrator
 * @Author dragon
 * @Date 2020-04-30 17:29
 * @Version 1.0
 */
public class StringUtils {

    public static boolean isNullOrEmpty(String content) {
        return content == null || content.length() <= 0;
    }

}
