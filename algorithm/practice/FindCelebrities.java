package com.chenjunyi.practice;

/**
 *  查询名人， knows(i,j) 判断i是否认识j, i->j 单项箭头，i 认识 j, 不代表j要认识i
 *
 *
 *  鞥称为明星：明星节点不认识其他所有人，其他所有人都认识明星
 */
public class FindCelebrities {

    public static boolean knows(int i, int j) {
        return true;
    }


    public static int findCelebrities(int n) {
        int cand = 0;
        // cand不认识所有人，cand右侧
        for (int i =0; i < n; i++) {
            if (knows(cand, i)) {
                cand = i;
            }
        }

        // cand 是唯一可能称为明星的节点，
        // cand的左侧， cand是不是不认识所有人
        // cand要不认识所有人，左侧
        for (int i =0; i < cand; i++) {
            if (knows(cand, i)) {
                return -1;
            }
        }

        // 是否所有人都认识cand， 所有人都要认识cand
        for (int i =0;i < n; i++) {
            if (!knows(i, cand)) {
                return -1;
            }
        }

        return cand;
    }
}
