package com.chenjunyi.zhongji二.zhongji1;

import java.util.HashMap;

/**
 *
 * 题目： 给定两个数组arr1和arr2, 长度都为N, 代表二维平面上有N个点，
 *      第i个点的x坐标和y坐标分别为arr1[x]和arr2[y]，返回求一条只限最多能穿过多少个点？
 *
 * 思路：x和y两个点之间存在着4种关系：(1) 共位置，即x.x==y.x && x.y==y.y      (2) 共x轴， 即x.x=y.x && x.y != y.y
 *                              (3) 共y轴， 即x.x != y.x && x.y==y.y   (4) 有斜率关系 |x.y-y.y| / |x.x-y.x|
 * gcd：最大公约数
 */
public class Code02_MaxPointOneLine {

    private static class Node{
        int x;
        int y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    public static void main(String[] args) {
        System.out.println(gcd(6,10));
        System.out.println(gcd(3,7));
        System.out.println(gcd(12,9));

    }

    private static int maxPointOneLine(Node[] points) {
        int result = 0;
        HashMap<String, Integer> map = new HashMap<>();
        for (int i =0; i < points.length-1; i++) {
            map.clear();
            int samePosition = 1;
            int sameX = 0;
            int sameY = 0;
            int line = 0;
            // Node0 和 Node1 之间的关系
            // Node0 和 Node2 之间的关系
            // Node0 和 Node3 之间的关系
            // Node0 和 Nodei 之间的关系
            for (int j = i+1; j < points.length; j++) {
                // （1）共位置
                if (points[i].x==points[j].x && points[i].y==points[j].y) {
                    samePosition++;
                } else if(points[i].y == points[j].y) {
                    sameY++;
                } else if(points[i].x == points[j].x) {
                    sameX++;
                } else {
                    // 斜率
                    int x = Math.abs(points[i].x=points[j].x);
                    int y = Math.abs(points[i].y=points[j].y);
                    int gcd = gcd(x, y);
                    int x_x = x/gcd;
                    int y_y = y/gcd;
                    String x_y = String.valueOf(x_x)+"_"+String.valueOf(y_y);
                    // 如果斜率表中包含该斜率，那么count++
                    if (map.containsKey(x_y)) {
                        map.put(x_y, map.get(x_y)+1);
                    } else {
                        map.put(x_y, 1);
                    }
                    // 记录下每个斜率的数量
                    line = Math.max(line, map.get(x_y));

                }

            }
            // 在斜率里面找一个最大的

            result = Math.max(result, Math.max(Math.max(sameX, sameY), line)) + samePosition;


        }

        

        return result;
    }



    // 辗转相除法, 求两个数的最大公约数
    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);

    }
}
