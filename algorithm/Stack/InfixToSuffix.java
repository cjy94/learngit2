package com.chenjunyi.Stack;

import java.math.BigDecimal;
import java.util.Stack;

/**
 * 中缀表达式转后缀表达式
 *
 * 从左向右遍历每一个字符
 * 1、如果是数字则直接输出；
 * 2、如果遇到左括号，入栈； 遇到右括号，说明括号内的表达式都处理完毕，将栈中元素弹出，直到遇到左括号为止；
 * 3、遇到运算符， 如果栈顶找中的运算符优先级高于当前运算符，那么弹出栈顶元素，接着比较新的栈顶元素，直到栈空或者栈顶元素的优先级小于当前，则入栈
 *
 *
 * 注：栈中只存放 运算符和括号，不存放数字
 *
 * 参考： https://blog.csdn.net/chen15369337607/article/details/108317116
 */
public class InfixToSuffix {


    public static String infixToSuffix(String str) {
        char[] c = str.toCharArray();
        Stack<Character> stack = new Stack();
        StringBuilder sb = new StringBuilder();
        // 从左到右遍历字符串
        for (char ch : c) {
           switch (ch) {
               // 过滤空格
               case ' ':
                   break;
                   // 遇到左括号直接入栈
               case '(':
                   stack.push(ch);
                   break;
               // 遇到'+'/'-',
               //在栈不空的情况下，将栈中所有运算符全部弹出，如果遇到'('为止
               // 栈空的话直接入栈
               case '+' :
               case '-' :
                   while (!stack.isEmpty()) {
                      char temp = stack.pop();
                      if (temp == '(') {
                          stack.push('(');
                          break;
                      }
                       sb.append(temp);
                   }
                   stack.push(ch);
                   break;
               // 遇到'*'/'-'
               // 在栈不空的情况下，全部弹出，遇到'+' '-' '('为止
               case '*':
               case '/':
                   while (!stack.isEmpty()) {
                       char temp = stack.pop();
                       if (temp == '+' || temp == '-' || temp == '(') {
                           stack.push(temp);
                           break;
                       }
                       sb.append(temp);

                   }
                   stack.push(ch);
                   break;
               case ')' :
                   while (!stack.isEmpty()) {
                       char temp = stack.pop();
                       if (temp == '(') {
                           break;
                       }
                       sb.append(temp);
                   }

                   break;
               default:
                   sb.append(ch);
                   break;
           }
        }

        // 如果栈中不空，将栈中元素输出
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    // 利用后缀表达式求值
    public static BigDecimal sum(String str) {
        char[] s = str.toCharArray();
        Stack<BigDecimal> stack = new Stack<>();
        for (char ch : s) {
            switch (ch){
                case '*':
                case '/':
                case '+':
                case '-':
                    BigDecimal x = stack.pop();
                    BigDecimal y = stack.pop();
                    stack.push(calculate(x, y, ch));
                    break;
                default: // 数字，直接入栈
                    stack.push(new BigDecimal(ch-'0'));
                    break;
            }
        }
        return stack.pop();

    }

    private static BigDecimal calculate(BigDecimal x, BigDecimal y, char ch) {
         BigDecimal temp = null;
         switch (ch) {
             case '+':
                 temp = y.add(x);
                 break;
             case '-':
                 temp = y.subtract(x);
                 break;
             case '*':
                 temp = y.multiply(x);
                 break;
             case '/':
                 if (x.intValue() == 0) {
                     throw new ArithmeticException("被除数不能是0！");
                 } else {
                     temp = y.divide(x);
                 }
                 break;
             default:
                 throw new ArithmeticException("unknow!");
         }
         return temp;
    }

    public static String infixToSuffix1(String expression) {
        // 创建操作符堆栈
        Stack<Character> stack = new Stack<>();
        // 要输出的后缀表达式字符串
        StringBuilder suffix = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            // 中间变量
            char temp;
            // 获取该中缀表达式的每一个字符进行判断
            char ch = expression.charAt(i);
            switch (ch) {
                // 过滤空格
                case ' ':
                    break;
                // 遇到左括号直接入栈
                case '(':
                    stack.push('(');
                    break;
                // 遇到+、- 运算符,将栈中的所有运算符全部弹出，遇到堆栈中的左括号为止
                case '+':
                case '-':
                    while (stack.size() != 0) {
                        temp = stack.pop();
                        if (temp == '(') {
                            stack.push('(');
                            break;
                        }
                        suffix.append(temp);
                    }
                    // 没有进入循环说明当前为第一次进入或者其他前面运算都有括号等情况导致栈已经为空,此时需要将符号进栈
                    stack.push(ch);
                    break;
                // 遇到*、/ 运算符,则将堆栈中的所有运算符全部弹出，遇到加号、减号以及左括号为止
                case '*':
                case '/':
                    while (stack.size() != 0) {
                        temp = stack.pop();
                        if (temp == '+' || temp == '-' || temp == '(') {
                            stack.push(temp);
                            break;
                        }
                        suffix.append(temp);
                    }
                    // 没有进入循环说明当前为第一次进入或者其他前面运算都有括号等情况导致栈已经为空,此时需要将符号进栈
                    stack.push(ch);
                    break;
                // 遇到右括号,则弹出所有运算符直到遇到左括号为止，并抛弃左括号。
                case ')':
                    while (!stack.isEmpty()) {
                        temp = stack.pop();
                        if (temp == '(') {
                            // 这里左括号不重新入栈了
                            break;
                        }
                        suffix.append(temp);
                    }
                    break;
                // 默认情况下，将作为操作数直接输出到队列中
                default:
                    suffix.append(ch);
                    break;
            }
        }
        // 如果堆栈不为空，则把剩余的运算符一次弹出，输出到队列中
        while (!stack.isEmpty()) {
            suffix.append(stack.pop());
        }
        // 返回后缀表达式
        return suffix.toString();
    }


    public static void main(String[] args) {
        String s = infixToSuffix("2*3+(5-4)+6*7");
        System.out.println(s);
        System.out.println(sum(s));
        BigDecimal d1 = new BigDecimal("2");
        BigDecimal d2 = new BigDecimal('2');  // ASCII码
        System.out.println(d1.intValue());
        System.out.println(d2.intValue());

    }
}
