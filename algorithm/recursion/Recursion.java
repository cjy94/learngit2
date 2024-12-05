package com.chenjunyi.recursion;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;
import java.util.TreeMap;

/**
 * 常见的经典递归问题
 *
 * 1、返回字符串全部子序列，子序列要求去重
 * 2、返回数组的所有组合，可以无视元素顺序，但要求去重
 * 3、返回没有重复值数组的全部排列，
 * 4、返回可能有重复值数组的全部排列，排列要求去重
 *
 * 5、用递归逆序一个栈
 * 6、用递归排序一个栈
 * 7、打印汉诺塔问题的最优移动轨迹
 *
 *
 *
 *  递归：
 * 1、基本计算器， 表达式求值
 *  用栈和递归实现起来方便
 *
 *
 *
 * 剪枝
 *
 *  任何递归都是dfs
 * 1、子串是要求连续的的；子序列可以连续的
 *    "a  b  c"     每个字符都有两种选择，要或者不要  子序列的个数: 2^3 = 8
 *     V  V  V
 *     X  X  X
 *
 * 2、[1,2,2]   所有组合： [[], [1], [1,2], [1,2,2], [2], [2,2]]
 * "" "1" "2" "1 2" "2" "1 2" "2 2" "1 2 2"
 *
 * 3、排列 [1 2 3]  123 132 213 231 321 312      3!： 3*2*1个
 */
public class Recursion {

    public static void main(String[] args) {
//       String s = "HMg2(N4)2OH";
//        TreeMap<String, Integer> stringIntegerTreeMap = decodeAtomic(s);
//        System.out.println(stringIntegerTreeMap);
        sort();
    }


    //debug
    static class Test {
        String timeStamp;
        String name;
        Test(String timeStamp, String name) {
            this.timeStamp = timeStamp;
            this.name = name;
        }
    }

    public static void sort() {
        List<Test> list = new ArrayList<>();
        list.add(new Test("1723675894833", "a"));
        list.add(new Test("1723675895170", "b"));
        list.add(new Test("1723675894879", "c"));
        list.add(new Test("1723675894317", "d"));
        list.add(new Test("1723675894904", "e"));
        list.add(new Test("1723675894980", "f"));
        list.add(new Test("1723675894833", "g"));
        Test[] tests = list.toArray(new Test[list.size()]);
        for (Test test : tests) {
            System.out.println(test.name + ", " + test.timeStamp);       
        }

        System.out.println("=====================================");
//        Collections.sort(list, new Comparator<Test>() {
//            @Override
//            public int compare(Test o1, Test o2) {
//                return Long.valueOf(o2.timeStamp).compareTo(Long.valueOf(o1.timeStamp));
//            }
//        });

        Collections.sort(list, ((a,b)-> {
            return Long.valueOf(b.timeStamp).compareTo(Long.valueOf(a.timeStamp));
        }));

        Test[] tests1 = list.toArray(new Test[list.size()]);
        for (Test test : tests1) {
            System.out.println(test.name + ", " + test.timeStamp);
        }
    }
    //debug

    // set集合是去重的
    public static void f1(char[] s, int index, StringBuilder path, HashSet<String> result) {
        // 来到字符串最后位置，将path放到result中
        if (index == s.length) {
            result.add(path.toString());
        }  else {
            // 要s[index]的位置字符
            path.append(s[index]);
            f1(s, index + 1, path, result);
            path.deleteCharAt(path.length() - 1);   // ******* 擦除路径 (重要!!!!!)
            // 不要s[index]位置的字符
            f1(s, index + 1, path, result);
        }
    }

    // 返回数组的所有组合，可以无视元素顺序，但要求去重
    public static void f2(int[] s, int index, int[] path, int size, List<List<Integer>> result) {
        // 来到数组末尾位置, 将path放到result结果集中
        if (index == s.length) {
            List<Integer> ans = new ArrayList<>();
            for (int i =0; i < size; i++) {
                ans.add(path[i]);
            }
            result.add(ans);
        } else {
            int j = index+1;
            while (j < s.length && s[index] == s[j]) {     // [1,1,1,1,2,2,2]  要0个1和后续的2组合；要1个1和后续的2组合；要2个1和后续的2组合；要3个1和后续的2组合...
                j++;
            }
            f2(s, j, path, size, result);
            for (; index < j; index++) {
                path[size++] = s[index];
                f2(s, j, path, size, result);
            }
        }
    }

    public static void f3(int[] nums, int index, List<List<Integer>> result) {
        // index来到数组结尾位置, 将结果放到result集合中
        if (index == nums.length) {
            List<Integer> cur = new ArrayList<>();
            for (int i =0; i < nums.length; i++) {
                cur.add(nums[i]);
            }
            result.add(cur);
        } else {
            HashSet<Integer> set = new HashSet<>();
            for (int i = index; i < nums.length; i++) {
                if (!set.contains(nums[i])) {
                    set.add(nums[i]);
                    swap(nums, i, index);
                    f3(nums, index + 1, result);
                    swap(nums, i, index);
                }
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    // 5、用递归逆序一个栈
    // 不要额外申请空间 (不使用额外的数据结构)

    // 移除栈底元素
    public static int removeBottom(Stack<Integer> stack) {
        int ans = stack.pop();
        if (stack.isEmpty()) {
            return ans;
        } else {
            int last = removeBottom(stack);
            stack.push(ans);
            return last;
        }
    }

    // 逆序一个栈
    public static void reverseStack(Stack<Integer> stack) {
        if(stack.isEmpty()) {
            return;
        }
        // 栈底元素
        int last = removeBottom(stack);
        reverseStack(stack);
        stack.push(last);
    }


    // 6、用递归排序一个栈
    // deep(stack): 计算栈深度
    // max(stack, deep): 返回栈在这个深度下的最大元素
    // times(stack, deep, max): 返回栈在这个深度下最大值出现的次数
    // down(stack, deep, max, times): 将这个栈中这个深度下的times个最大值压入栈底，其他元素的相对唏嘘保持不变
    public static void sortStack(Stack<Integer> stack) {
        int deep = deep(stack);
        while (deep != 0) {
            int max = max(stack, deep);
            int times = times(stack, deep, max);
            down(stack, deep, max, times);
            deep -= times;
        }
    }

    private static int deep(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return 0;
        }
        int num = stack.pop();
        int deep = deep(stack) + 1;
        stack.push(num);
        return deep;
    }

    private static int max(Stack<Integer> stack, int deep) {
        if (deep == 0) {
            return Integer.MIN_VALUE;
        }
        int num = stack.pop();
        int nextMax = max(stack, deep-1);
        int max = Math.max(num, nextMax);
        stack.push(num);
        return max;
    }

    private static int times(Stack<Integer> stack, int deep, int max) {
        if (deep == 0) {
           return 0;
        }
        int num = stack.pop();
        int nextTimes = times(stack, deep-1, max);
        int times = nextTimes + (num == max ? 1 : 0);
        stack.push(num);
        return times;
    }

    // 改变stack数据状况
    // 将times个max 移动到栈底，其他元素的相对次序保持不变
    private static void down(Stack<Integer> stack, int deep, int max, int times) {
        if (deep == 0) {
            for (int i =0; i < times; i++) {
                stack.push(max);
            }
        } else {
            int num = stack.pop();
            down(stack, deep-1, max, times);
            if (num != max) {
                stack.push(num);
            }
        }
    }


    /**
     * 解析字符串， 例如： ABC3[de]yhacd5[rd] 解析成： ABCdededeyhacdrdrdrdrdrd
     */
    public static String decodeString(String s) {

        return f(s.toCharArray(), 0).result;

    }

    static class Info{
        int end;
        String result;
        public Info(String result, int end) {
            this.end = end;
            this.result = result;
        }
    }

    private static Info f(char[] s, int index) {
        int len = s.length;
        StringBuilder str = new StringBuilder();
        int num = 0;
        while (index < len && s[index] != ']') {
            // 遇到数字
            if (s[index] >= '0' && s[index] <= '9') {
                num = num * 10 + (s[index++] - '0');
            } else if(s[index] >= 'a' && s[index] <= 'z' || (s[index] >= 'A' && s[index] <= 'Z')) {   // 遇到字母
                str.append(s[index++]);
            } else {  // 遇到了'['
                Info data = f(s, index + 1);
                index = data.end + 1;
                str.append(getString(num, data.result));
                num = 0;
            }
        }
        return new Info(str.toString(), index);
    }

    // get(3, "abc")  返回 abcabcabc
    private static String getString(int num, String str) {
        StringBuilder s = new StringBuilder();
        for (int i =0; i < num; i++) {
            s.append(str);
        }
        return s.toString();
    }

    /**
     * 分子式： 统计每个原子出现的数量
     *  TreeMap: 有序表， 有序表
     */
      static class ReturnData {
          TreeMap<String, Integer> result;
          int end;

          public ReturnData(TreeMap<String, Integer> result, int end) {
              this.result = result;
              this.end = end;
          }
      }

    /**
     * 分子式解析， HMg2N(GH)5
     */
    public static TreeMap<String, Integer> decodeAtomic(String s) {
        return f1(s.toCharArray(), 0).result;
    }

    private static ReturnData f1(char[] str, int i) {
        TreeMap<String, Integer> result = new TreeMap<>();
        StringBuilder s = new StringBuilder();
        TreeMap<String, Integer> pre = null;
        int len = str.length;
        int num = 0;
        while (i < len && str[i] != ')') {
            // 遇到数字
            if (str[i] >= '0' && str[i] <= '9') {
                num = num * 10 + (str[i++] - '0');
            } else if (str[i] >= 'a' && str[i] <= 'z') {  // 遇到小写字母 , 前面一定跟着一个大写字母
                 s.append(str[i++]);
            } else {   // 遇到大写字母或者'('
                fill(result, s, pre, num);    // 将之前的一部分结果可能是pre中的结果或者s中的结果，添加到result整体结果中
                num = 0;
                pre = null;
                s.setLength(0);
                if (str[i] >= 'A' && str[i] <= 'Z') {
                    s.append(str[i++]);
                } else {   // 遇到'('
                    ReturnData data = f1(str, i+1);
                    i = data.end + 1;
                    pre = data.result;
                }
            }
        }
        fill(result, s, pre, num);
        return new ReturnData(result, i);

    }

    private static void fill(TreeMap<String, Integer> result, StringBuilder str, TreeMap<String, Integer> pre, int count) {
        if (pre != null || str.length() > 0) {
            count = count == 0 ? 1 : count;
            if (pre != null) {
                for (String key : pre.keySet()) {
                    result.put(key, result.getOrDefault(key, 0) + pre.get(key) * count);
                }

            } else {
                result.put(str.toString(), result.getOrDefault(str.toString(), 0) + count);
            }
        }
    }


    // 同余原理       减法同余公式 (a-b) % mod = (a%mod - b%mod + mod) % mod
    // 计算 ((a+b) * (c-d) + (a*c - b*d)) % mod
    public static int mod1(long a, long b, long c, long d, int mod) {
        int o1 = (int) (a % mod);
        int o2 = (int) (b % mod);
        int o3 = (int) (c % mod);
        int o4 = (int) (d % mod);
        int o5 = (o1 + o2) % mod;
        int o6 = (o3 - o4 + mod) % mod;
        int o7 = (int)(((long)(o1 * o3)) % mod);
        int o8 = (int)(((long)(o2 * o4)) % mod);
        int o9 = (int)(((long)(o5 * o6)) % mod);
        int o10 = (o7 - o8 + mod) % mod;
        int o11 = (o9 + o10) % mod;
        return o11;
    }


    // 打印一个数的二进制形式

    public static void print(long num) {
        StringBuilder str = new StringBuilder();
        for (int i =63; i >= 0; i--) {
            str.append((num & (1L << i)) == 0 ? "0" : "1");
        }
        System.out.println(str.toString());
    }





}
