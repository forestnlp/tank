package com.tank.prototype;

public class StringTest {


    public static void main(String[] args) {

        String s1 = "hello"; //一般来说 优先去常量池寻找

        String s2 = new String("hello");//创建在堆里
        System.out.println("--------------------------");
        System.out.println(s1==s2);// s1 和 s2 不同，指向不同区域（方法区和堆）的对象。
        System.out.println(s1==s2.intern());//会把s2堆里的字符串做一个优化，指向常量池。
        System.out.println(s1.intern()==s2.intern());//会把s2堆里的字符串做一个优化，指向常量池。

        String s3="hell"+"o";
        System.out.println("--------------------------");
        System.out.println(s1==s2); //不等，是在堆空间不同的对象
        System.out.println(s1==s3); //此时相等，是在堆空间的同一个对象，因为s3做了编译优化？
        System.out.println(s1.intern()==s3.intern()); //等，此时去池里找了
        System.out.println(s2.intern()==s3.intern()); //等，此时去池里找了

        String s4="hell"+new String("o");
        System.out.println("--------------------------");
        System.out.println(s1==s4); //不等，是在堆空间不同的对象
        System.out.println(s1==s4); //此时不等，是在堆空间的同一个对象，因为s4无法做编译优化
        System.out.println(s1.intern()==s4.intern()); //等，此时去池里找了
        System.out.println(s2.intern()==s4.intern()); //等，此时去池里找了

    }

}
