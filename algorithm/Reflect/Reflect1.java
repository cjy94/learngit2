package com.chenjunyi.Reflect;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * oracle官网中对反射的定义：
 *  反射让开发人员可以通过外部类的全限定名称获取对象，并使用
 *  反射可以让开发人员枚举出类的全部成员，包括构造函数、属性、方法
 *  测试时可以利用反射API访问私有盛有，保证测试覆盖率
 */
public class Reflect1 {

    public static class User{
        public int age;
        private String name;
        final int version = 10;
        public static int COUNT = 23;
        private static int NUM = 300;

        User() {

        }
        public User(String name) {
            this.name = name;
            this.age = 10;
        }
        private User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public int getAge() {
            return age;
        }

        public String getName() {
            return name;
        }

        public void setAge(int age) {
            this.age = age;
        }

        private void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "age=" + age +
                    ", version=" + version +
                    ", COUNT=" + COUNT +
                    ", NUM=" + NUM +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        getUserClass();
        //getUserConstructor();
        //getUserFields();
        //getUserMethod();

    }

    // 如何反射获取Class对象
    private static void getUserClass() throws ClassNotFoundException {
        User u = new User("zs");
        Class<User> userClass = User.class;
        Class<? extends User> aClass = u.getClass();
        Class<?> aClass1 = Class.forName(User.class.getName());

        System.out.println((userClass==aClass) );
        System.out.println((userClass==aClass1) );
        System.out.println((aClass==aClass1) );

        

    }

    private static void getUserConstructor() {
        User u = new User("zs");
        Class<User> userClass = User.class;
        // 获取的时public修饰的构造函数
        System.out.println(Arrays.toString(userClass.getConstructors()));
        // 获取的时public、private、default修饰的构造函数
        System.out.println(Arrays.toString(userClass.getDeclaredConstructors()));
    }

    private static void getUserFields() {
        Class<User> userClass = User.class;
        // 只获取public 修饰的fields
        System.out.println(Arrays.toString(userClass.getFields()));
        // 获取public、private、default修饰的所有fields
        System.out.println(Arrays.toString(userClass.getDeclaredFields()));

    }

    private static void getUserMethod() {
        Class<User> userClass = User.class;
        // 只获取public 修饰的fields 包括父类
        System.out.println(Arrays.toString(userClass.getMethods()));
        // 获取public、private、default修饰的所有fields 不包含父类中的信息
        System.out.println(Arrays.toString(userClass.getDeclaredMethods()));


    }


    private static void test1(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        User u1 = new User("zs");

        /**
         * 获取Class对象的方式
         * 1、类名.class
         * 2、对象.getClass()
         * 3、全限定类名： Class.forName("")
         */
        // 方式一
        // 普通对象
        Class<User> userClass = User.class;
        User user = userClass.newInstance();
        Field version = userClass.getDeclaredField("version");
        version.setAccessible(true);
        System.out.println("set final field version====");
        System.out.println("before: "+user);
        version.set(user, 200);
        System.out.println("after: " + user);
        System.out.println("set common field age==");
        Field age = userClass.getDeclaredField("age");
        System.out.println("before: "+user);
        age.set(user, 200);
        System.out.println("after: " + user);
        System.out.println("set public static field count: ");
        Field count = userClass.getDeclaredField("COUNT");
        System.out.println("before: " + user);
        count.set(user, 10000);
        System.out.println("after: " + user);
        System.out.println("set private static field num: ");
        Field num = userClass.getDeclaredField("NUM");
        System.out.println("before: " + user);
        num.set(user, 20000);
        System.out.println("after: " + user);

    }
}
