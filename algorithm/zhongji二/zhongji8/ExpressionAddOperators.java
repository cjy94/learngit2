package com.chenjunyi.zhongji二.zhongji8;
import java.util.*;

/**
 * 数字组成的字符串中添加'+' '-' '*'表达式， 使其表达式计算后等于target
 * 
 */
public class ExpressionAddOperators {
    public static Set<String> getAllResult(String num, int target) {
        Set<String> ans = new HashSet<>();
        char[] str = num.toCharArray();
        process(str, 0, target, "", ans);
        return ans;

    }

    // 从str[i]位置出发，决定他后面的字符(+/-/*)
    private static void process(char[] str, int i, int target,String path, Set<String> ans) {
        if (i == str.length) {
            char last = path.charAt(path.length()-1);
            path = (last == '+' || last == '-' || last == '*') ? path.substring(0, path.length()-1) : path;
            if (check(path, target)) {
                ans.add(path);
            }
            return;
        }

        String p0 = String.valueOf(str[i]);
        String p1 = p0+"+";
        String p2 = p0+"-";
        String p3 = p0+"*";
        process(str, i+1, target, path + p0,ans);
        process(str, i+1, target, path + p1,ans);
        process(str, i+1, target, path + p2,ans);
        process(str, i+1, target, path + p3,ans);
    }

    private static void addNum(LinkedList<String> queue, int num) {
        if (!queue.isEmpty()) {
            String top = queue.pollLast();
            if (top.equals("*") || top.equals("/")) {
                int cur = Integer.valueOf(queue.pollLast());
                num = top.endsWith("*") ? (cur * num) : (cur / num);
            } else {
                queue.addLast(top);
            }
        }
        queue.addLast(String.valueOf(num));
    }


    private static int p (String str) {
        int ans = 0;
        LinkedList<String> queue = new LinkedList<>();
        char[] s = str.toCharArray();
        for (char ch : s) {
            queue.addLast(String.valueOf(ch));
        }


        return ans;
    }

    // path是正常的公式字符串， 检查是否计算的结果和target一样
    private static boolean check(String path, int target) {
        return calExp(path) == target;
    }

    private static int getNum(LinkedList<String> queue) {
        int res = 0;
        boolean add = true;
        int num = 0;
        String cur = null;
        while (!queue.isEmpty()) {
            cur = queue.pollFirst();
            if (cur.equals("+")) {
                add = true;
            } else if (cur.equals("-")) {
                add = false;
            } else {
                num = Integer.valueOf(cur);
                res += add ? num : (-num);
            }
        }
        return res;
    }


    public static int calExp(String express){
        LinkedList<Character> op = new LinkedList<>();
        LinkedList<Integer> data = new LinkedList<>();
        int num = 0;
        for (int i = 0; i < express.length(); i++) {
            char c = express.charAt(i);
            //如果是数据
            if(Character.isDigit(c)){
                //入操作数栈
                num = num * 10 + (c-'0');
                if (i+1 == express.length()) {   // 接收的字符串是正常的表达式， 最后的一个字符一定是数字
                    data.addFirst(num);
                }
            }  else {
                data.addFirst(num);
                num = 0;
                Character stackTopOp = null;
                //如果操作符栈不为空，获取操作符栈顶元素
                if(!op.isEmpty()){
                    stackTopOp = op.getFirst();
                }

                //如果操作符栈为空 或者 当前操作符的优先级高于操作符栈顶元素的优先级
                if(op.isEmpty() ||getOpPriority(c,stackTopOp)){
                    // 如果当前运算符的优先级高于操作符栈顶的优先级，那么直接入栈；否则将栈顶运算符弹出进行计算
                    //当前操作符入栈
                    op.addFirst(c);
                } else {
                    //取出操作符栈顶元素
                    Character opTop = op.removeFirst();
                    //取出操作数栈顶两个元素
                    int b = data.removeFirst();
                    int a = data.removeFirst();
                    //计算
                    int resTmp = calculate(a, b, opTop);
                    //将计算完的结果入栈
                    data.addFirst(resTmp);

                    //最后将当前操作符入栈
                    op.addFirst(c);
                }
            }

        }

        //最后遍历整个操作符栈，此时操作符栈中的优先级都是一致的。
        while (!op.isEmpty()) {
            Character opTop = op.removeFirst();
            Integer b = data.removeFirst();
            Integer a = data.removeFirst();
            int res = calculate(a, b, opTop);
            data.addFirst(res);
        }

        //最后操作数栈中只剩一个元素，就是计算结果
        return data.getFirst();
    }

    /**
     * 判断两个运算符之间的优先级
     * 如果c1运算符优先级大于c2运算符优先级，则返回true
     * 否则返回false
     * @param c1
     * @param c2
     * @return
     */
    private static boolean getOpPriority(Character c1,Character c2){
        if((c1.equals('*') || c1.equals('/')) &&
                (c2.equals('+') || c2.equals('-'))){
            return true;
        }else{
            return false;
        }
    }


    public static int calculate(int a,int b, char op){
        switch (op){
            case '*':
                return a*b;
            case '/':
                return a/b;
            case '+':
                return a+b;
            case '-':
                return a-b;
            default:
                throw new IllegalArgumentException();
        }
    }


    public static void main(String[] args) {
//        ExpressionAddOperators e = new ExpressionAddOperators();
        String num1 = "12*3";
        num1 = "123";
        int value = calExp(num1);
        System.out.println(value);
        String num = "123";
        Set<String> allResult = getAllResult(num, 6);
        System.out.println(allResult);
    }
}
