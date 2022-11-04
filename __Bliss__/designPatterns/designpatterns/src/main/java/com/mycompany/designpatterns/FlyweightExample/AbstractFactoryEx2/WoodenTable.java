package com.mycompany.designpatterns.FlyweightExample.AbstractFactoryEx2;

public class WoodenTable implements Table{
    @Override
    public void create() {
        System.out.println("Create a wooden table");
    }
}
