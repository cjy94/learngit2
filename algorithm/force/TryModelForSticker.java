package com.chenjunyi.force;

/**
 *
 * 给定一个字符串str，给定一个字符串类型的数组arr
 * arr里的每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，目的是频出str来
 * 返回需要至少多少张贴纸可以完成这个任务
 * 例子: str="babac" arr={"ba","c", "abcd"}
 * 至少需要两张贴纸“ba”和“abcd”, 因为使用这两张贴纸，把每一个字符单独剪开，含有2个a、2个b、和1个c
 *
 *
 * 尝试要求， 可变参数尽量少
 *
 */
public class TryModelForSticker {
    public static int minStickers(String[] stickers, String target ) {
        // 将stickers中的贴纸转换成词频统计
        // 比如: aabc -> [2,1,1,0,0,0,0,0,0,0]
        int[][] map = new int[stickers.length][26];
        // 遍历每一张贴纸将每一张贴纸的词频 都统计出来
        for (int i =0; i < stickers.length; i++) {
            char[] chars = stickers[i].toCharArray();
            for (char ch : chars) {
                map[i][ch-'a']++;
            }
        }

        return process1(map, target);
    }

    /**
     * 用当前贴纸，搞定target种的字符后，剩余的字符串target-first->nextTarget 继续用后续的所有贴纸搞定的方法数
     * @param map
     * @param rest
     * @return
     */
    private static  int process1(int[][] map, String rest) {
        // 将rest 也生成词频统计数组， 每次拿贴纸到rest的词频中进行减操作
        int[] tmap = new int[26];
        char[] target = rest.toCharArray();
        int ans = Integer.MAX_VALUE;
        for (char ch : target) {
            tmap[ch-'a']++;
        }

        // 遍历每一张贴纸，用当前贴纸减去tmap中的词
        for (int i =0; i < map.length; i++) {
            if (map[i][target[0]-'a'] == 0) {
                continue;
            }
            StringBuilder str = new StringBuilder();
            // 用第i号贴纸，j 枚举a~z 字符
            for (int j =0; j < 26; j++) {
                if (tmap[j] > 0) {  // 这个字符是target种需要的
                    // 用map种的贴纸减去target种的字符
                    for (int k =0; k < Math.max(0, tmap[j]- map[i][j]); k++) {
                        // 将贴纸搞顶后剩余的字符添加到stringBuilder种
                        str.append('a'+j);
                    }
                }
            }
            String nextStr = str.toString();
            // 剩余的字符串被后续贴纸搞定的方法
            int p1 = process1(map, nextStr);
            if (p1 != -1) {
                ans = Math.min(ans, 1 + p1);
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static void main(String[] args) {
        String[] str = {"aaac", "bc", "cc", "d", "f"};
        String target = "bbbbbbbbbbc";
        minStickers(str, target);

    }

}
