package com.chenjunyi.day10_List;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class FindPart {
    public static void main(String[] args) {
        List<String> append = append();
        System.out.println(find(append));

    }


    public static List<String> append() {

        // 生成规则并且匹配， 如果dirs目录下有这个文件夹， 则输出
        ArrayList<String> paths = new ArrayList<>();
        int left = 19945;
        int right = 20214;
        for (int i = left; i <= right; i++) {
            for (int j = i; j<= right; j++) {
                String str = "20230330_";
                if (i == j) {
                    str = str + i +"_" +j + "_0";
                    paths.add(str);
                } else {
                    str = str + i +"_" +j + "_1";
                    paths.add(str);
                }
            }

        }
        return paths;



    }

    public static List<String> find(List<String> paths) {
        String[] dirs = {"/data07/ckk/store/5eb/5eb15116-a578-46ae-b2e5-d5d05b28b577",
                "/data08/ckk/store/5eb/5eb15116-a578-46ae-b2e5-d5d05b28b577",
                "/data09/ckk/store/5eb/5eb15116-a578-46ae-b2e5-d5d05b28b577",
                "/data10/ckk/store/5eb/5eb15116-a578-46ae-b2e5-d5d05b28b577",
                "/data11/ckk/store/5eb/5eb15116-a578-46ae-b2e5-d5d05b28b577"};


        ArrayList<String> lists = new ArrayList<>();
        for (String dir : dirs) {
            String[] list = new File(dir).list();
            for (String l : list) {
                if (paths.contains(l)) {
                    lists.add(dir + "/" + l);
                }
            }
        }
        return lists;
    }

}
