package com.mycompany.designpatterns.FlyweightExample.AbstractFactoryEx2;

public class WoodenChair implements Chair{
    @Override
    public void create() {
        System.out.println("Create a wooden chair");
    }
}
