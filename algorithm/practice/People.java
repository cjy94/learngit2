package com.chenjunyi.practice;

import org.openjdk.jol.info.ClassLayout;

/**
 * 5a39699c  --> Hashcode 值  十六进制      电脑是小端存储
 * ==============================
 * com.chenjunyi.practice.People object internals:
 *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
 *       0     4        (object header)                           01 9c 69 39 (00000001 10011100 01101001 00111001) (963222529)
 *       4     4        (object header)                           5a 00 00 00 (01011010 00000000 00000000 00000000) (90)
 *       8     4        (object header)                           08 08 24 00 (00001000 00001000 00100100 00000000) (2361352)
 *      12     4    int People.age                                0
 * Instance size: 16 bytes
 * Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
 */
public class People {
    int age;


    public static void main(String[] args) {
        People p = new People();
        System.out.println(ClassLayout.parseInstance(p).toPrintable());
        System.out.println(Integer.toHexString(p.hashCode()));
        System.out.println("==============================");
        System.out.println(ClassLayout.parseInstance(p).toPrintable());

    }
}
