package com.chenjunyi.GO;

/**
 *  位图
 *  1024           1024 / 32=32
 *  int[32] 可以表示1024个数出现还是没出现过
 *  int[0] : 0-31
 *  int[1] : 32-63
 *  int[2] : 64-95
 *   ...
 *   ...
 *
 *   long类型是8bytes, 64bit, 所以一个long类型可以表示64个数出现还是没出现过
 *
 *   java中注意 1 和 1L的区别
 *
 *   系统最小值转绝对值, 还是他自己，无法转换
 *   
 */
public class BitMap {
    long[] bits;

    public BitMap(int size) {
        bits = new long[(size+64) / 64];
    }

    // num/64: 确定num属于哪个整数，也就是bits数组的索引
    // num / 64 等价于 (num >> 6)
    // num%64: 确定在bits[i]这个整数上的第几位
    // (num % 64) 等价于 (num & 63)
    public void add(int num) {
        bits[num >> 6] |= (1L << (num & 63));
        // 等价于
        // bits[num/64] |= (1L << (num % 64));
    }

    public void delete(int num) {
        bits[num >> 6] &= (~(1L << (num & 63)));
        // 等价于
        // bits[num/64] &= ~(1L << (num % 64));
    }

    public boolean contains(int num) {
        /**
         *  bits[num>>6] bits数组中的这个数的二进制都摊在这
         *  num % 64 这个位上的这个数是否是1?
         */
        return (bits[num>>6] & (1L << (num&63))) != 0;
    }

}
