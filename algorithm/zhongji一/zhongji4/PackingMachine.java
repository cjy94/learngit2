package com.chenjunyi.zhongji一.zhongji4;

/**
 * 洗衣机问题
 */
public class PackingMachine {

    public static int minOps(int[] arr) {
        int size = arr.length;
        int sum = 0;
        for (int i =0; i < size; i++) {
            sum += arr[i];
        }
        if (sum % size != 0) {
            return -1;
        }

        int avg = sum / size;
        int leftNum = 0;
        int ans = 0;
        for (int i =0; i < arr.length; i++) {
            int leftRest = leftNum - avg * i;
            int rightRest = (sum - leftNum- arr[i])-(size-i-1) * avg;  // 实际的衣服 - 需要的衣服
            if (leftRest < 0 && rightRest < 0) {
                    ans = Math.max(ans, Math.abs(leftRest) + Math.abs(rightRest));
            } else {
                ans = Math.max(ans, Math.max(Math.abs(leftRest), Math.abs(rightRest)));
            }

            leftNum += arr[i];
        }
        return ans;
    }

    
}
