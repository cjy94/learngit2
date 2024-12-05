package com.chenjunyi.practice;

/**
 *   可以被k个数组成一个数的数组长度的公式满足： (length-k) % (k-1) == 0
 */
public class KNumber {

    public static int process(int l, int r, int P, int[] arr, int k, int[] preSum) {
        if (l == r)
            return P == 1 ? 0 : -1;
        if (P == 1) {
            int next = process(l, r, P, arr, k, preSum);
            if (next != -1)
                return next + (preSum[r+1] - preSum[l]);
            else
                return -1;
        } else {
            int ans = Integer.MAX_VALUE;
            for (int i = l; i < r; i += k -1) {
                int left = process(l, i, 1, arr, k, preSum);
                int right = process(i + 1, r, P - 1, arr, k, preSum);
                if (left != -1 && right != -1)
                    ans = Math.min(ans, left + right);
                else
                    return -1;
            }
            return ans;
        }
    }

}
