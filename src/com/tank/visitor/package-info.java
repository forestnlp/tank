package com.tank.visitor;



/**
 *  computer 持有 cpu board memory . 这些内部对象。这些对象可以称为Compenent
 *
 *  对象提供了一个accept(visitor v)的方法。
 *
 *  visitor在访问Component的时候，visitor的方法。
 *
 *  常用语编译器，解析器等。对于一个结构内部所有节点的检查。
 *
 *  asm就使用了这种模式
 *
 */