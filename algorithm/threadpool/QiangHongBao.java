package com.chenjunyi.threadpool;

import java.io.File;
import java.util.ArrayList;

public class QiangHongBao {


    static ArrayList<Integer> list = new ArrayList<>(10);

    public static void process(File file) {
        File[] files = file.listFiles();
        if (file == null || files.length == 0) {
            return;

        }

        for (File f : files) {
            if (f.isDirectory()) {
                process(f);
            } else {
                System.out.println(f.getName());
            }
        }
    }





//
//    static List<String> ergodic(File file, List<String> resultFileName) {
//        File[] files = file.listFiles();
//        if (files == null)
//            return resultFileName;// 判断目录下是不是空的
//        for (File f : files) {
//            if (f.isDirectory()) {// 判断是否文件夹
//                //resultFileName.add(f.getPath());
//                ergodic(f, resultFileName);// 调用自身,查找子目录
//            } else
//                resultFileName.add(f.getPath());
//        }
//        return resultFileName;

    public static void main(String[] args) {
        System.out.println(list.size());
         File f = new File("D:\\data02");
        process(f);
    }
}
