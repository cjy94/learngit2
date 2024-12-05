package com.chenjunyi.project;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 *  虚拟游戏
 * 可以投资1个产品，也可以投资2个产品
 */
public class VirtualGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] info = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int count = info[0];    // 产品数量
        int ztouzi = info[1];   // 总投资
        int zfengxian = info[2];  // 总风险

        // 产品回报率
        int[] b = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        // 产品风险值
        int[] r = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        // 产品投资额
        int[] i = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int firstIndex = 0;
        int stouzi = 0;
        int sfengxian = Integer.MAX_VALUE;
        HashMap<Integer, Integer> map = new HashMap<>();
        while (firstIndex < count) {
            if (r[firstIndex] <= zfengxian) {   // 可以投资，投资多少是在总投资和当前产品可投资之间选择最小值
                int firstTouzi = Math.min(ztouzi, i[firstIndex]);
                int touzi = firstTouzi * b[firstIndex];
                if (touzi > stouzi || touzi == stouzi && r[firstIndex] < sfengxian) {
                    stouzi = touzi;
                    sfengxian = r[firstIndex];
                    map.clear();
                    map.put(firstIndex, firstTouzi);
                }

            } else {
                firstIndex++;
                continue;
            }

            int secondIndex = firstIndex + 1;
            while (secondIndex < count) {
                if (r[secondIndex] + r[firstIndex] <= zfengxian) {  // 可以投资
                    int firstTouzi;
                    int secondTouzi;
                    if (i[firstIndex] > i[secondIndex]) {
                        firstTouzi =  Math.min(ztouzi, i[firstIndex]);
                        secondTouzi = Math.min(ztouzi - firstTouzi, i[secondIndex]);
                    } else if(i[firstIndex] < i[secondIndex]) {
                        firstTouzi = Math.min(ztouzi, i[secondIndex]);
                        secondTouzi = Math.min(ztouzi - firstTouzi, i[firstIndex]);
                    } else if (r[firstIndex] > r[secondIndex] ) {
                        firstTouzi = Math.min(ztouzi, i[secondIndex]);
                        secondTouzi = Math.min(ztouzi - firstTouzi, i[firstIndex]);
                    }  else {
                        firstTouzi = Math.min(ztouzi, i[firstIndex]);
                        secondTouzi = Math.min(ztouzi - firstTouzi, i[secondIndex]);
                    }
                    int touzi = firstTouzi * b[firstIndex] + secondTouzi * b[secondIndex];
                    int fengxian = r[firstIndex] + r[secondIndex];
                    if (touzi > stouzi || touzi == stouzi && fengxian < sfengxian) {
                        stouzi = touzi;
                        sfengxian = fengxian;
                        map.clear();
                        if (firstTouzi > 0) map.put(firstIndex, firstTouzi);
                        if (secondTouzi > 0) map.put(secondIndex, secondTouzi);
                    }

                }
                secondIndex++;
            }
            firstIndex++;
            
        }

        StringBuilder s = new StringBuilder();
        for (int x =0; x < count; x++) {
            if (map.containsKey(x)) {
               s.append(map.get(x));
            } else {
                s.append("0");
            }
        }
    }
}
