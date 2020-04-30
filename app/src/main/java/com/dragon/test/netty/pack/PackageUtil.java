package com.dragon.test.netty.pack;

/**
 * @ClassName PackageUtil
 * @Description TODO
 * @SysUser Administrator
 * @Author dragon
 * @Date 2020-04-30 11:35
 * @Version 1.0
 */
public class PackageUtil {

    public final static byte BIT_8 = Byte.MAX_VALUE;
    public final static short BIT_16 = Short.MAX_VALUE;
    public final static int BIT_32 = Integer.MAX_VALUE;
    public static final long BIT_64 = Long.MAX_VALUE;

    //读取自适应数字
    public static Number autoReadNum(int offset, byte[] bytes) {
        byte bit = bytes[offset];
        offset++;
        Number ret = null;
        switch (bit) {
            case 9: //readLong
                ret = 0;
                break;
        }
        return ret;
    }

    //写入自适应数据
    public static byte autoWriteNum(int offset, Number value, byte[] ret) {
        byte bit = getAutoWriteLen(value);
        // TODO: 2020-04-30 writeByte
        offset++;
        switch (bit) {
            case 9:
                //entity code
                break;
        }
        return bit;
    }

    //获取自适应数字大小
    public static byte getAutoWriteLen(Number value) {
        long v = Math.abs(value.longValue());
        byte bit = 0;
        //如果数值为0，则返回1
        if (v == 0) {
            bit = 1;
        } else if (v > BIT_32) {//..................
            bit = 9;
        } else if (v > BIT_16) {//..................
            bit = 5;
        } else if (v > BIT_8) {//..................
            bit = 3;
        } else {//..................
            bit = 2;
        }
        return bit;
    }

}
