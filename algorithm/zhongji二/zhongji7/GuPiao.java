package com.chenjunyi.zhongji二.zhongji7;

/**
 * 股票问题:  arr[]
 * 数组代表，每一个时刻的股票家个
 */
public class GuPiao {

    // 问题1：只能做一次交易，并且每次买卖一股，获得的最大收益是多少
    public static int maxValue1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int min = arr[0];
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            // 遍历到当前节点是，前面的最小值
            min = Math.min(min, arr[i]);
            ans = Math.max(arr[i]- min, ans);
        }
        return ans;

    }

    // 问题2：如果随便交易， 且每次交易只买卖一股，返回最大收益
    //  只要左边值比右边大，就累加上去
    public static int maxValue2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int pre = arr[0];
        int ans = 0;
        int index = 1;
        while (index < arr.length){
            int cur = arr[index];
            if (cur > pre) {
                ans += (cur - pre);
            }
            pre = cur;
            index++;
        }
        return ans;
    }

    // 问题3：你想知道如果交易次数不超过k次， 且每次交易只买卖一股，返回最大收益
    // 和问题2的区别就是 限制了交易次数
    // 如果k超过了数组的长度的一半， 等同于随意交易，可以使用maxValue2
    public static int maxValue3(int[] arr, int k) {
        
        return 0;

        

        
    }

    public static int search(int[] nums, int target) {
        if (nums==null || nums.length == 0) {
            return -1;
        }
        if (nums.length == 1 && nums[0] == target) {
            return 0;
        }
        if (nums.length == 1 && nums[0] != target) {
            return -1;
        }

        int l = 0;
        int r = nums.length-1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            // 如果刚好在中间位置， 那么直接返回
            if (target == nums[mid]) {
                return mid;
            }
            // 如果不是那么需要考虑在那边查找，
            // 没有旋转过的有序数组，一定是target<arr[mid] 去左边查找；target>arr[mid] 去右边查找
            // 但是旋转过的数组，则需要检查target和左边、中间值的位置关系
            if (nums[l] <= nums[mid]) {
                // target 在数组的左侧部分， 则左侧继续找
                if(target<=nums[mid] && target>=nums[l]){
                    r=mid-1;
                }else{
                    // 否则右侧继续找
                    l=mid+1;
                }
            } else { // arr[l] > arr[mid]  发生过旋转，arr[l] != arr[mid] 因为无重复值

                if(target>=nums[mid] && target<=nums[r]){  // 如果target在右侧部分
                    l=mid+1;
                }else{
                    r=mid-1;
                }
            }

        }
        return -1;


    }

    public static void main(String[] args) {
        int[] arr = {3,1};
        System.out.println(search(arr, 1));
    }
}
