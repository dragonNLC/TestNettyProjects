package com.dragon.test.netty.utils;

/**
 * @ClassName ConvertUtils
 * @Description TODO
 * @SysUser Administrator
 * @Author dragon
 * @Date 2020-04-30 10:18
 * @Version 1.0
 * &0xFF的意义在于执行清位操作，把不必要的脏数据去掉，保留一个byte范围数据
 * 大端模式的意义在于，将高位放在低字节,把低位放在高字节
 * 对应一个数据int value = 0xfeef，那么它在内存中的存放类型有
 * 大端模式
 * >>>>>>>>>>>>内存增大方向
 * fe       ef
 * 小端模式
 * >>>>>>>>>>>>内存增大方向
 * ef       fe
 */
public class ConvertUtils {

    /***
     * covert int to byteArray
     * @param i value of int
     * @return result of byteArray
     */
    public static byte[] int2Bytes(int i) {
        byte[] result = new byte[4];//int占4位
        result[0] = (byte) ((i >> 24) & 0xFF);
        result[1] = (byte) ((i >> 16) & 0xFF);
        result[2] = (byte) ((i >> 8) & 0xFF);
        result[3] = (byte) (i & 0xFF);
        return result;
    }

    /**
     * covert byteArray to int
     *
     * @param bytes
     * @return
     */
    public static int bytes2Int(byte[] bytes) {
        int value = 0;
        for (int i = 0; i < bytes.length; i++) {
            int shift = (3 - i) * 8;//取位，看byte是需要位移多少位
            value += (bytes[i] & 0xFF) << shift;//对位做左移操作，并将结果值保存上去
        }
        return value;
    }

    public static String bytes2String(byte[] bytes) {
        StringBuilder sb = new StringBuilder();//not safe with MTD(multi thread)
        for (int i = 0; i < bytes.length; i++) {
            byte b = (byte) (bytes[i] & 0xFF);//先把每一个byte取出来
            for (int j = 7; j >= 0; j--) {
                sb.append(b >> j & 1);
            }
        }
        return sb.toString();
    }

    public static String byte2String(byte b) {
        StringBuilder sb = new StringBuilder();//not safe with MTD(multi thread)
        b = (byte) (b & 0xFF);//先把每一个byte取出来
        for (int j = 7; j >= 0; j--) {
            sb.append(b >> j & 1);
        }
        return sb.toString();
    }

}
