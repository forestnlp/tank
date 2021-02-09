package com.tank.template;

public abstract class Father {

    void m(){
        System.out.println("m start");
        p1();
        p2();
        System.out.println("m end");
    }

    abstract void p1();

    abstract void p2();

}

class Son extends Father{

    @Override
    void p1() {
        System.out.println("p1");
    }

    @Override
    void p2() {
        System.out.println("p2");
    }

    public static void main(String[] args) {
        Father f = new Son();
        f.m();
    }
}

