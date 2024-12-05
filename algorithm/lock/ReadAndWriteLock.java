package com.chenjunyi.lock;

/**
 *  读写锁： state一个32位的整型变量， 高16位表示写锁的个数，低16位表示读锁的个数
 *  在获取写锁时：如果state==0, 直接获取； 如果state != 0， 获取低16位的值，如果低16位是0，表示没有线程持有写锁，但是有线程持有读锁，并且持有这个锁的线程不是本线程，这时候获取写锁失败；
 *              写锁的个数达到了上限(1<<16)也获取锁失败
 *
 *              写锁获取成功的条件：1、state==0，获取成功；
 *                                2、 state != 0, 但是持有锁的线程是自己，获取成功
 *
 *
 *
 *  获取读锁：1、如果其他线程持有写锁，获取失败；
 *           2、其他情况遵循AQS原则，锁数量上限等条件约束外，都可以获取到读锁
 *
 */

public class ReadAndWriteLock {
    public static void main(String[] args) {
        print(-1);

    }

    public static void print(int num) {
        StringBuilder str = new StringBuilder();
        for (int i = 31; i >= 0; i--) {
            str.append(((1L << i) & num) == 0 ? "0" : "1");
        }
        System.out.println(str);
    }
}
