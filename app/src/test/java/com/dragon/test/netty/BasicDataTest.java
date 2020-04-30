package com.dragon.test.netty;

import com.dragon.test.netty.utils.ConvertUtils;

import org.junit.Test;

/**
 * @ClassName BasicDataTest
 * @Description TODO
 * @SysUser Administrator
 * @Author dragon
 * @Date 2020-04-30 10:03
 * @Version 1.0
 */
public class BasicDataTest {

    @Test
    public void doTestBit() {
        /*System.out.println(Byte.MAX_VALUE);
        System.out.println(Byte.MIN_VALUE);
        int bit2 = 0b1101;
        bit2 = bit2 | ~bit2;
        System.out.println(bit2);*/

        /*short v = 256;
        byte[] ret = new byte[2];
        ret[0] = (byte) (v >> 8);
        ret[1] = (byte) v;

        System.out.println(ret[0] + "-" + ret[1]);*/

        byte[] buf = new byte[]{(byte) 255, 1};

        String result = ConvertUtils.byte2String((byte) 255);
        System.out.println(result);
    }

}
