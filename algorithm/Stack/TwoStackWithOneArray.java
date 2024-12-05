package com.chenjunyi.Stack;

/**
 * 使用一个数组，实现两个栈，并且保证只要数组中还有剩余空间，栈操作就不能提示异常
 */
public class TwoStackWithOneArray {
    private int[] arr;
    private int oneStack;
    private int twoStack;
    int size;
    public TwoStackWithOneArray(int size) {
        if (size < 2) {
            throw new IllegalArgumentException("size < 2 is no persmissible!");
        }
        this.size = size;
        arr = new int[size];
        oneStack = -1;  // 还没插入元素之前，指针置为无效
        twoStack = size;

    }

    // 向哪个栈中插入数据
    // stackId是栈编号， value是要插入的值
    public void push(int stackId, int value) {
       // 检查数组是否还有空间
        if (oneStack +1 == twoStack) {
            throw new RuntimeException("栈满");
        }
        // 还有空间，向第一个栈插入元素
        if (stackId == 1) {
            arr[++oneStack] = value;
        } else if(stackId == 2) {
            arr[--twoStack] = value;
        } else {
            return;
        }
    }

    // 需要取哪个栈中的元素
    public int pop(int stackId) {
        if (stackId == 1) {
            if (oneStack == -1) {
                throw new RuntimeException("oneStack栈空");
            }
            int value = arr[oneStack];
            oneStack--;
            return value;
        } else if(stackId == 2) {
            if (twoStack == size) {
                throw new RuntimeException("twoStack 栈空");
            }
            int value = arr[twoStack];
            twoStack++;
            return value;
        }else {
            throw new RuntimeException("非法的栈编号");
        }


    }

    public int peek(int stackId) {
        if (stackId == 1) {
            if (oneStack == -1) {
                throw new RuntimeException("oneStack栈空");
            }
            return arr[oneStack];
        } else if(stackId == 2) {
            if (twoStack == size) {
                throw new RuntimeException("twoStack 栈空");
            }
            return arr[twoStack];
        } else {
            throw new RuntimeException("非法的栈编号");
        }
    }

    public boolean isEmpty(int stackId) {
        if (stackId == 1) {
            return oneStack == -1;
        } else if(stackId == 2) {
            return twoStack == size;
        } else {
            throw new RuntimeException("非法的栈编号");
        }
    }
}
