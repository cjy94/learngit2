package com.chenjunyi.Stack;

/**
 * 在一个数组中实现3个栈
 */
public class ThreeStackWithOneArray {
    private int[] arr;
    private int oneStack;    // 第一个栈应该插入元素的索引位置
    private int twoStack;    // 第二个栈应该插入元素的索引位置
    private int threeStack;  // 第三个栈应该插入元素的索引位置
    private int baseThree;  // 第三个栈的起始位置
    // 第一个个栈[0,oneStack] 第二个栈[twoStack, size-1]  第三个栈[baseThree, threeStack]
    private int size;

    ThreeStackWithOneArray(int size) {
        this.size = size;
        this.oneStack = -1;
        this.twoStack = size;
        this.threeStack = size/2;
        this.baseThree = threeStack;
        arr = new int[size];
    }

    // 向指定的栈中插入元素
    public void push(int stackId, int value) {
        if (stackId == 1) {
            if (oneStack +1 == baseThree) {
                if (stack3IsRightShift()) {
                    shiftStack3ToRight();
                    arr[++oneStack] = value;
                }  else {
                    throw new RuntimeException("栈满");
                }
            } else {
                arr[++oneStack] = value;
            }
        } else if (stackId == 2) {
            if (twoStack-1 == threeStack) {
                if (stack3IsLeftShift()) {
                    shiftStack3ToLeft();
                    arr[--twoStack] = value;
                } else {
                    throw new RuntimeException("栈满");
                }

            }else {
                arr[--twoStack] = value;
            }
        } else if (stackId == 3) {
            if (threeStack+1 == twoStack) {
                if (stack3IsLeftShift()) {
                    shiftStack3ToLeft();
                    arr[++threeStack] = value;
                } else {
                    throw new RuntimeException("栈满");
                }

            } else {
                arr[++threeStack] = value;
            }
        } else {
            throw new RuntimeException("unknow");
        }
    }

    private void shiftStack3ToRight() {
        for (int i = threeStack+1; i > baseThree; i--) {
            arr[i] = arr[i-1];


        }
        baseThree--;
        threeStack--;
    }

    private boolean stack3IsRightShift() {
        return threeStack+1 < twoStack;
    }

    private void shiftStack3ToLeft() {
        for (int i = baseThree-1; i < threeStack; i++) {
            arr[i] = arr[i+1];
        }
        baseThree--;
        threeStack--;
    }

    // 能否向左移动
    private boolean stack3IsLeftShift() {
        return baseThree -1 > oneStack ;
    }

    // 弹出元素
    public int pop(int stackId) {
        if (stackId == 1) {
            if (oneStack == -1) {
                throw new RuntimeException("栈空");
            }
            return arr[oneStack--];
        } else if (stackId == 2) {
            if (twoStack == size) {
                throw new RuntimeException("栈空");
            }
            return arr[twoStack++];
        } else if (stackId == 3) {
            if (baseThree == threeStack) {
                throw new RuntimeException("栈空");
            }
            return arr[threeStack--];
        } else {
            throw new RuntimeException("unknow");
        }
    }

    public int peek(int stackId) {
        if (stackId == 1) {
            if (oneStack == -1) {
                throw new RuntimeException("栈空");
            }
            return arr[oneStack];
        } else if (stackId == 2) {
            if (twoStack == size) {
                throw new RuntimeException("栈空");
            }
            return arr[twoStack];
        } else if (stackId == 3) {
            if (baseThree == threeStack) {
                throw new RuntimeException("栈空");
            }
            return arr[threeStack];
        } else {
            throw new RuntimeException("unknow");
        }

    }

    public boolean isEmpty(int stackId) {
        if (stackId == 1) {
            return oneStack == -1;
        } else if(stackId == 2) {
            return twoStack == size;
        } else if(stackId == 3) {
            return baseThree == threeStack;
        } else {
            throw new RuntimeException("unknow");
        }
    }



}
