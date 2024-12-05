package com.chenjunyi.project;

import java.util.Scanner;

/**
 *  分配信道问题
 *  贪心???
 */
public class FenPeiSignal {

    public static void maxUser() {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int R = in.nextInt();
            int[] N = new int[R+1];
            for (int i =0; i < R+1; i++) {
                N[i] = in.nextInt();
            }
            int D = in.nextInt();
            int num = getResult(R, N, D);
            System.out.println(num);
        }
    }

    private static int getResult(int r, int[] n, int d) {
        int useCapacity = 0;
        int ans = 0;
        for (int i = r; i >= 0; i--) {
            int curCapacity = (int)Math.pow(2, i);
            for (int j =0; j < n[i]; j++) {
                useCapacity += curCapacity;
                if (useCapacity >= d) {
                    ans++;
                    useCapacity = 0;
                }
            }
        }
        return ans;
    }


}
