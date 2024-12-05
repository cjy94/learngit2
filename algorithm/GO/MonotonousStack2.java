package com.chenjunyi.GO;

import java.util.Calendar;

/**
 *  单调栈
 *  1、最大宽度坡
 *  给定一个整数数组A，破是元组(i,j)，其中 i<j 且 A[i] <= A[j]
 *  这样的破的宽度为j-i, 找出A中的破的最大宽度，如果不存在，返回0
 *
 *  栈中存放可以作为坡的左侧位置如果arr[i]比栈顶元素小，则进栈，否则不进栈
 *  栈底到栈顶的数组顺序是从大到小
 *  然后从右往左遍历数组，数组中的元素和栈顶元素能否组成一个宽度坡
 *
 *  2、去除重复字母保证剩余字符串的字典序最小
 *  一个字符串s， 请去除字符串中重复字母，使得每个字母只出现一次
 *  需保证 返回结果的字典序最小
 *  要求不能打乱其他字符的相对位置
 *
 *  思路： 先统计字符串中每个字符的词频信息； 在将字符依次进栈，字符进栈的要求是：比栈顶字符的字典序大可以进栈，否则需要弹出栈顶元素，
 *      但是栈顶元素是否能弹出也是有条件的，如果栈顶的字符词频已经是0了，也就是后面这个字符不再出现了，那么这个字符不能弹出，在栈中不动，比他小的字符依然进栈
 *
 *    全部结束后，栈中[0,r]的字符数组就是满足要求的最小的字典序
 *
 *  3、大鱼吃小鱼问题     难 但是有趣的一种实现
 *  给定一个数组arr，每个值代表鱼的体重， 每一轮，每条鱼都会吃掉右边离自己最近比自己体重小的鱼，每条鱼向右找只吃一条
 *  但是吃鱼这件事是同时发生的，也就是同一轮在A吃掉B的同时，A也可能被别的鱼吃掉
 *  如果有多条鱼在当前轮找到的是同一条小鱼，那么在这一轮，这条小鱼同时被这些大鱼吃
 *  请问多少轮后，鱼的数量就固定了
 *
 *    思路：从右往左遍历数组，进栈
 *
 *  4、统计全1子矩阵的数量
 *
 *  找到左侧比我小的理我最近的位置left，右侧比我小的离我最近的位置right，[left+1, right-1]这个范围是我真正处理的范围
 *  处理以Math.max(arr[left+1], arr[right-1]) ~ arr[i] 之间的数作为高，统计出所有的矩形数量
 *
 *                    1
 *                    1
 *                    1                 1
 *     1              1                 1
 *     1              1                 1
 *     1              1                 1
 *
 *     3  ...         6     ...         8
 *   left            cur                i
 *
 *  如上图，假设位置6从栈中弹出，6位置的高度是6
 *  6位置左边、离6最近、且高度小于6的3位置(left)，3位置高度是3
 *  6位置右边、离6最近、且高度小于6的8位置(i)，8位置高度是4
 *  此时我们求什么？
 *  1) 求在4~7范围上必须以高度6最为高的矩形有多少个?
 *  2) 求在4~7范围上必须以高度5最为高的矩形有多少个?
 *  也就是说，<=4的高度一律不求，>6的高度一律不求!
 *  那么在4~7范围上吗，高度是6的矩形有几个?
 *  4~4 4~5 4~6 4~7
 *  5~5 5~6 5~7
 *  6~6 6~7
 *  7~7
 *  10个，公式是 4*5/2
 *  同理，在4~7范围上，高度是5的矩形也是这么多
 *  所以cur从栈中弹出的矩形数量是:
 *      cur位置的高度-Max(left位置的高度，i位置的高度) * ((i-left-1)*(i-left)/2)
 *
 *
 */
public class MonotonousStack2 {
    public static void main(String[] args) {
        System.out.println(Calendar.getInstance().get(Calendar.DAY_OF_YEAR));
    }


    // 重复字母问题
    public static String Duplicate(String s) {
        if (s == null || s.length() == 0)
            return s;

        char[] ch = s.toCharArray();
        int n = ch.length;
        int[] frequency = new int[n];
        for (char v : ch) {
            frequency[v-'a']++;
        }
        int[] stack = new int[n];
        boolean[] enter = new boolean[n];
        int r = 0;  // 指向栈顶元素的指针
        for (char cur : ch) {
            if (enter[cur-'a'] == false) {
                while (r > 0 && cur < stack[r - 1] && frequency[stack[r-1] - 'a'] > 0) {
                    enter[stack[r-1] - 'a'] = false;
                    r--;
                }
                stack[r++] = cur;
                enter[cur-'a'] = true;
            }
            frequency[cur - 'a']--;
        }
        return new String(stack, 0, r);
    }


    // 全1子矩阵数量, 栈底到栈顶的元素大小是从小到大，大数值压小数值
    public static int countRectangle(int[] arr) {
        int n = arr.length;
        int[] stack = new int[n]; // 从小到大
        int r = 0;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            while (r > 0 && arr[i] <= arr[stack[r-1]]) {  // 相等情况弹出不弹出都可以
                int cur = stack[--r];  // 栈中放的是索引值，不是真正的数值，arr[cur]是数值
                if (arr[cur] > arr[i]) {   // 严格大于才统计
                    int left = r == 0 ? -1 : stack[r - 1];
                    int max = Math.max(left == -1 ? 0 : arr[left], arr[i]);
                    ans += (arr[cur] - max) * (i - left + 1) * (i - left) / 2;
                }
            }
            stack[r++] = i;
        }
        return ans;
    }
    
}
