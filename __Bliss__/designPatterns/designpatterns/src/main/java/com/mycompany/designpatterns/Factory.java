package com.mycompany.designpatterns;

import com.mycompany.designpatterns.FactoryExample.Computer;
import com.mycompany.designpatterns.FactoryExample.Pc;
import com.mycompany.designpatterns.FactoryExample.Server;

/**
 * Factory - SuperClass - SubClass. a superclass with multiple sub-classes and based on input, Factory return one of the sub-class
 */
public class Factory {
    public static final String getContext(){
        String context = "Super class is `Computer`. " +
                " Subclasses are `Pc` and `Server` (They are all `Computer`). ";
        System.out.println(context);
        return context;
    }

    public static Computer getComputer(String type, String ram, String hdd, String cpu){
        if("Pc".equalsIgnoreCase(type)) return new Pc(ram, hdd, cpu);
        else if("Server".equalsIgnoreCase(type))return new Server(ram, hdd, cpu);
        return null;
    }
}
