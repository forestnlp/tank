package com.tank.adpter;

/**
 * 就是一个转换器
 *
 * 比如asm中的adapter等，适配了其他的vistor
 *
 *
 * 误区：这不是适配器模式
 * windowlistner 接口 --> window adapter 抽象类 （具体方法实现了listner的方法，只需要继承adapter类，再覆盖部分方法就可以了）
 *
 */