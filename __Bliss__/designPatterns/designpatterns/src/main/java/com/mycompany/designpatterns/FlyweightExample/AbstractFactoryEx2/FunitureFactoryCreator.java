package com.mycompany.designpatterns.FlyweightExample.AbstractFactoryEx2;

public class FunitureFactoryCreator {
    public static FunitureFactoryAbstract getFunitureFactory(String type){
        if(type == "plastic") return new PlasticFunitureFactory();
        return new WoodenFunitureFactory();
    }
}
