package com.tank.builder;
/**
 *  builder 模式用于构建负责对象
 *
 *  特征：1、静态内部类存储 目标对象。构建后返回。
 *       2、链式调用
 *
 *
 *       eg: Class Person;
 *       public static class PersonBuilder
 *       {
 *           Person p = new Person();
 *
 *           public PersonBuilder buildAge(int age){
 *               p.age = age;
 *               return this;
 *           }
 *
 *           public PersonBuilder buildName(String name){
 *               p.name = name;
 *               return this;
 *           }
 *
 *           public Person build(){
 *               return p;
 *           }
 *       }
 *
 *
 */