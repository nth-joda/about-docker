package com.mycompany.designpatterns.FlyweightExample.AbstractFactoryEx2;

public class PlasticTable implements Table{
    @Override
    public void create() {
        System.out.println("Create a plastic Table");
    }
}
