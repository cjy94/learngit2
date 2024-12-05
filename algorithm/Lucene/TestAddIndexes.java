package com.chenjunyi.Lucene;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class TestAddIndexes {

    public static void main(String[] args) throws Exception {
//        String w = "D:\\lucene\\query.txt";
//        String r = "D:\\lucene\\query17.txt";
//        rewrite(r, w);
        
        test();





    }

    static void test() {
        while (true) {

        }

    }


    static void rewrite(String rpath, String wpath) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(rpath)));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(wpath), "UTF-8"));
        String str = "";
        long id = 1L;
         while ((str = br.readLine()) != null) {
             JSONObject data = JSON.parseObject(str);
             data.put("id", id);
             id++;
             bw.write(JSON.toJSONString(data));
             bw.write(System.lineSeparator());
         }
         bw.close();

    }

}
