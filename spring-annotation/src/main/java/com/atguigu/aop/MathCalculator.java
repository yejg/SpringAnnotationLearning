package com.atguigu.aop;

import com.atguigu.bean.Person;

public class MathCalculator {

    public int div(int i, int j) {
        System.out.println("MathCalculator...div...");
        return i / j;
    }

    public String test(Integer inte, String str, Person person) {
        return "test";
    }

}
