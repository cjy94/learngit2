package com.chenjunyi.classLoader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 自定义类的加载器
 * loadClass() 体现了双亲委派机制
 * findClass() 真正加载类的方法 其中调用defineClass()
 *
 *
 *  JVM 支持两种类型的类加载器，分别为引导类加载器(Bootstrap ClassLoader)和自定义类加载器(User-Defined ClassLoader)
 *  Bootstrap ClassLoader：C语言实现的类加载器
 *  其他类型的 ClassLoader：Java语言实现的类加载器
 *
 *  
 *
 *
 *  双亲委派机制
 *
 *
 */
public class UserClassLoader extends ClassLoader {
    private String rootDir;
    public UserClassLoader(String rootDir) {
        this.rootDir = rootDir;
    }

    // 编写findClassLoader的逻辑
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // 获取class文件字节数组
        byte[] classData = getClassData(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        }  else {
            return defineClass(name, classData, 0, classData.length);
        }
    }

    // 编写获取class文件并转换为字节数组
    private byte[] getClassData(String className) {
        String path = clasxNameToPath(className);
        try{
            InputStream ins = new FileInputStream(path);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = ins.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            return baos.toByteArray();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String clasxNameToPath(String className) {
        return rootDir + "\\" + className.replace('.', '\\') + ".class";
    }

    public static void main(String[] args) throws ClassNotFoundException {
        String rootDir = "D:\\IdeaCode\\study\\src";
        // 创建自定义类的加载器1
        UserClassLoader loader1 = new UserClassLoader(rootDir);
        Class clazz1 = loader1.findClass("com.chenjunyi.classLoader.User");

        // 创建自定义类的加载器2
        UserClassLoader loader2 = new UserClassLoader(rootDir);
        Class clazz2 = loader2.findClass("com.chenjunyi.classLoader.User"); // 全类名： 包名+类名
        System.out.println(clazz1 == clazz2); //   false
        System.out.println(clazz1.getClassLoader());
        System.out.println(clazz2.getClassLoader());

        //###################
        Class clazz3 = ClassLoader.getSystemClassLoader().loadClass("com.chenjunyi.classLoader.User");
        System.out.println(clazz3.getClassLoader());
        

    }

}
