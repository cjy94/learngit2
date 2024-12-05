package com.chenjunyi.GO;

import java.util.Arrays;

/**
 *  双指针技巧
 *
 *  常见的双指针类型：
 *  1) 同向指针
 *  2) 快慢指针
 *  4) 从两头往中间的双指针
 *  5) 其他
 *
 *  题目：
 *  1、 按奇偶排序数组
 *  给定一个非负整数数组nums，nums中一半整数是奇数， 一半整数是偶数
 *  对数组进行排序，以便当nums[i]为奇数时，i也是奇数；当nums[i]为偶数时，i也是偶数
 *
 *  不同的说法，同一个题目
 *  给定一个数组arr, 请把数组arr调整成奇数在奇数的位置，偶数在偶数的位置 ， 不一定非得有序，
 *  比如：
 *  [3 5 4 6 2 7 4 9]  ---->  [4 3 2 5 6 7 4 9]
 *
 *  两个指针，even偶数位指针， odd奇数位指针， 一开始偶数位指针在0位置上，奇数位指针在1位置上，偶数位指针在最小的偶数位上，奇数位指针在最小的奇数位上
 *  arr[even] %2 == 0，则even+2； 否则swap()
 *  arr[odd] %2 != 0, 则odd+2； 否则
 *
 *
 *  2、寻找重复数
 *   给定一个包含n+1 个整数的数组nums，其数字都在[1,n]范围内(包括1和n)
 *   可知至少存在一个重复数，请返回这个重复数，
 *
 *   比如nums长度是6(n+1)， 数组中的数值范围在1-5(n)之间
 *   [1,3,4,2,2]  重复数值2
 *   思路： 每个数值应该在i-1索引位置上    nums[i]应该在i-1索引位置上   这种方式会修改数组
 *   要求求解过程中不能修改数组arr中的数值，同时空间复杂度是O(1)
 *
 *   使用 快慢指针  *********
 *
 *  3、接雨水
 *  给定n个非负整数表示每个宽度为1的柱子的高度图， 计算按此排序的柱子，下雨之后能接多少雨水
 *   比如： height= [0 1 0 2 1 0 1 3 2 1 2]    输出: 6
 *   l = 1, r = n-2,     lmax(左侧0...l-1位置的最大值), rmax(右侧r+1 ... n-1位置的最大值)
 *   height[l] 和 height[r] pk， 哪一侧的最大值小，就先结算哪一部分
 *
 *   二维的接雨水问题 ???
 *
 *  4、救生艇
 *  给定数组people，people[i]表示第i个人的体重，船的数量不限制，
 *  每艘船可以承载的最大重量为limit, 每艘船最多可以同时承载2人，但条件是这些人的重量之和最多为limit，
 *  返回 承载所有人所需要的最小船数
 *
 *
 *  先将people数组排序
 *   4.1 从数组的后面开始遍历，如果有值超过了limit，那么就怎么也装载不了这些人；
 *   4.2 如果最大值小于等于limit, l=0, r=n-1
 *    [1,1,2,3,5,5,6,6,7,8,9,11]   limit=12
 *      1) [l]+[r] > limit, r自己一艘船，r--， 船数+1
 *      2) [l]+[r] <= limit, l和r2个人一艘船，l++, r--, 船数+1
 *
 *  进阶：再增加一个要求，如果两人一船那么体重之和必须是偶数，也该怎么做？
 *  将数组people分成两份数组，一个奇数数组，一个偶数数组
 *
 *  5、盛水最多的容器，
 *   给定一个长度为n的整数数组height
 *   有n跳垂线，第i条线的两个端点是(i,0)和(i，height[i])
 *   找出其中的两条线，使得他们与x轴共同构成的容器可以容纳最多的水，返回容器可以存储的最大水量，说明：不能倾斜容器
 *   l=0, r=n-1,    height[l]和height[r]这两个数值哪个小就先结算谁
 *
 *
 *  6、供暖器
 *  冬季已经来临，你的任务是设计一个有固定加热半径的供暖器向所有房屋供暖
 *  在加热器的加热半径范围内的每个房屋可以获取供暖
 *  现在，给出位于一条水平线的房屋houses和供暖器heaters的位置
 *  请找出并返回可以覆盖所有房屋的最小加热半径
 *  说明：所有供暖器都遵循你的半径标准，加热的半径也一样
 *
 *  将houses和hearts排序，
 *
 *
 *  7、缺失的第一个正数
 *  给你一个未排序的整数数组nums，请找出其中没有出现的最小正整数
 *  整个数组i位置的数值应该放置i+1数值，首个做不到的位置上就是缺失的数值
 *  0位置放1
 *  1位置放2
 *  2位置放3 ...
 *   7.1) arr[l] = l+1, l++
 *   7.2) arr[l] <= l, 垃圾
 *   7.3) arr[l] > r, 垃圾
 *   7.4) arr[arr[l]-1] == arr[l], 垃圾
 *   7.5) 交换
 *
 */

public class DoublePointers {

    public static int findMissingPositive(int[] arr) {
        // l的左边，都是做到i位置上放着i+1的区域
        // 永远盯着r位置的数字看，
        int l = 0;
        // [r...] 垃圾区
        // 最好的情况下，认为1~r是可以收集全的，每个数组收集1个，不能有垃圾
         int r = arr.length;
        while (l < r) {
            if (arr[l] == l+1) {
                l++;
            } else if (arr[l] <= l || arr[l] > r || arr[arr[l]-1] == arr[l]) {
                r--;
            } else {
                swap(arr, l , arr[l]-1);
            }
        }
        return l+1;
    }

    // 供暖器问题
    public static int findRadius(int[] houses, int[] hearts) {
        Arrays.sort(houses);
        Arrays.sort(hearts);
        int ans = 0;
        for (int i =0, j =0; i < houses.length; i++) {
            while (!best(houses, hearts, i, j)) {  // i是房屋标号，j是供暖器编号，这个循环是要找到最优的供暖器位置来给houses[i]房屋供暖
                j++;
            }
            ans = Math.max(ans, Math.abs(hearts[j]-houses[i]));
        }
        return ans;
    }

    private static boolean best(int[] houses, int[] hearts, int i, int j) {
        return j == hearts.length-1 || Math.abs(hearts[j] - houses[i]) < Math.abs(hearts[j+1] - houses[i]);
    }

    // 盛水问题
    public static int maxWater(int[] height) {
        int n = height.length;
        int ans = 0;
        for (int l =0, r = n-1; l < r;) {
            ans += Math.max(ans, Math.min(height[l], height[r]) * (r-l));
            if (height[l] < height[r])
                l++;
            else
                r--;
        }
        return ans;
    }

    // 救生艇1
    public static int RescueBoat1(int[] people, int limit) {
        Arrays.sort(people);
        int n = people.length;
        int l = 0, r = n-1;
        int ans = 0;
        int sum = 0;
        while (l <= r) {
            sum = l == r ? people[l] : people[l] + people[r];
            if (sum > limit) {
                r--;
            } else {
                l++;
                r--;
            }
            ans++;
        }
        return ans;
    }


    //parity 奇偶性
    public static void sortArrayByParity(int[] arr) {
        int n = arr.length;
        int even = 0, odd = 1, cur = n-1;
        while (even <= cur && odd <= cur) {
            if (arr[cur] % 2 == 0) {   // arr[cur] & 1 == 1
                swap(arr, even, cur);
                even += 2;
            } else {       // arr[cur] & 1 == 1    奇数
                swap(arr, odd, cur);
                odd += 2;
            }
        }
    }

    private static void swap(int[] arr, int i , int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 寻找重复数值
    public static int findDuplicate(int[] nums) {
        int n = nums.length;
        int slow = nums[0];
        int fast = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        // 相遇了，快指针回到0位置，继续快指针和慢指针各前进1步，直到相遇
        fast = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    // 接雨水
    public static int trap(int[] nums) {
        int n = nums.length;
        int l = 1, r = n-2, lmax = nums[0], rmax = nums[n-1];
        int ans = 0;
        while (l <= r) {
            if (lmax < rmax) {
                ans += Math.max(0, lmax - nums[l]);
                lmax = Math.max(lmax, nums[l++]);
            } else {
                ans += Math.max(0, rmax - nums[r]);
                rmax = Math.max(rmax, nums[r--]);
            }
        }
        return ans;
    }

    
}
