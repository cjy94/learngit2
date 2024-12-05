package com.chenjunyi.zhongji一.zhongji11;

public class KTimesOneTimes {
    public static void main(String[] args) {
//        int[] test1 = {1,1,1,2,2,2,6,6,6,9,9,9,12,12,12,10};
//        System.out.println(onceNum(test1, 3));

        int best = (int) Math.pow(10,7);
        System.out.println(best);

    }

    public static int onceNum(int[] arr, int k) {
        int[] eor = new int[32];
        for (int i =0; i < arr.length; i++) {
            setExclusiveOr(eor, arr[i], k);
        }

        int res = getNumFromKTimesNum(eor, k);
        return res;
    }

    private static int getNumFromKTimesNum(int[] eor, int k) {
        int res = 0;
        for (int i =eor.length-1; i >= 0; i--) {
            res = res *k+eor[i];
        }
        return res;


    }

    private static void setExclusiveOr(int[] eor, int value, int k) {
        int[] curKTime = getKNum(value, k);
        for (int i=0; i < eor.length; i++) {
            eor[i] = (eor[i] + curKTime[i]) %k;
        }


    }

    private static int[] getKNum(int value, int k) {
        int[] res = new int[32];
        int index = 0;
        while (value != 0) {
            res[index++] = value % k;
            value = value / k;
        }
        return res;
    }


}
