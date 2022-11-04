package com.mycompany.designpatterns.FlyweightExample.AbstractFactoryEx2;

public class PlasticChair implements Chair{
    @Override
    public void create() {
        System.out.println("Create a plastic chair");
    }
}
