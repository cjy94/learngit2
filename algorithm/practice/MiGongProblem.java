package com.chenjunyi.practice;

/**
 * leetcode 499
 * 球一旦拨动，只有撞到墙或者撞到障碍物才会停
 * 为了让球进入洞中，求最少的波动次数
 */
public class MiGongProblem {

    // 节点的位置[r,c], 节点从哪个方向来的, 之前做了哪些决定来到这个位置p
     static class Node{
        int r;
        int c;
        int d;
        String p;

        public Node(int r, int c, int d, String p) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.p = p;
        }
    }

    public static String[] re = {"d", "l", "r", "u"};
    public static int[][] to = {{1,0}, {-1,0}, {0,1}, {0,-1}, {0,0}};


    // maze: 迷宫信息
    // ball: 一个二元组，[0][1]初始球所在的位置，
    // hole： 一个二元组， 洞位置信息
    public static String findMinWays(int[][] maze, int[] ball, int[] hole) {
        int n = maze.length;
        int m = maze[0].length;
        Node[] q1 = new Node[n*m], q2 = new Node[n*m];
        int s1 = 0; int s2 = 0;
        boolean[][][] visit = new boolean[n][m][4];
        // 球在初始位置，向4个方向分裂的结果信息存储在q1数组中
        s1 = spread(maze,n,m,new Node(ball[0], ball[1], 4, ""),visit,q1,s1);
        while (s1 != 0) {
            // 遍历第一层分裂的每一个节点，在向下一层分裂
            for (int i =0; i < s1; i++) {
                Node cur = q1[i];
                // 如果已经分裂到洞中，则直接返回
                if (cur.r == hole[0] && cur.c == hole[1]) {
                    return cur.p;
                }
                // 继续分裂下一层
               s2 = spread(maze,n,m,cur,visit,q2,s2);
            }
            // 下一层的节点信息分裂到了q2数组中，q1 q2交换，继续下一层分裂
            Node[] tmp = q1;
            q1 = q2;
            q2 = tmp;
            s1 = s2;
            s2 = 0;
        }
        return "impossible";

    }


    // maze: 迷宫
    // n, m : 迷宫的大小
    // cur: 当前来到的节点 cur -> (r,c) 方向 路径  决定
    // v: [行][列][方向]
    // q:下一层的队列
    // s： q数组填到了哪个位置
    // 当前点cur， 分裂/走 所产生的下一层节点，进去队列q, s++
    // 返回值：当前层的点，该分裂分裂，该继续走继续走，所产生的下一层的队列，有几个点
    public static int spread(int[][] maze, int n, int m, Node cur, boolean[][][] v, Node[] q, int s) {
        return 0;
    }



    

}
