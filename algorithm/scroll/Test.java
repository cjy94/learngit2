 package com.chenjunyi.scroll;

 import java.io.File;

 public class Test {
   public static void main(String[] args) {
     String path1 = "D:\\lucene\\java";
     File file1 = new File(path1);
     System.out.println("是否存在：" + file1.exists());
     System.out.println("是否是目录" + file1.isDirectory());
     System.out.println("是否是文件" + file1.isFile());
     System.out.println(file1.getParent());


     String path2 = "D:\\lucene\\java\\1.json";
     File file2 = new File(path2);
     System.out.println("是否存在：" + file2.exists());
     System.out.println("是否是目录" + file2.isDirectory());
     System.out.println("是否是文件:" + file2.isFile());
     System.out.println(file2.getParent());
   }
 }


/* Location:              C:\Users\chenjunyi\Desktop\ck\scroll-7.7.0.jar!\com\scroll\Test.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */