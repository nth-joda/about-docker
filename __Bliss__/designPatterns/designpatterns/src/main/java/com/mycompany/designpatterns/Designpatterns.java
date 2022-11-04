/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.designpatterns;

import com.mycompany.designpatterns.FlyweightExample.AbstractFactoryEx2.Chair;
import com.mycompany.designpatterns.FlyweightExample.AbstractFactoryEx2.FunitureFactoryAbstract;
import com.mycompany.designpatterns.FlyweightExample.AbstractFactoryEx2.FunitureFactoryCreator;
import com.mycompany.designpatterns.FlyweightExample.AbstractFactoryEx2.Table;
import com.mycompany.designpatterns.AbstractFactoryExample.ComputerFactory;
import com.mycompany.designpatterns.AbstractFactoryExample.PcFactory;
import com.mycompany.designpatterns.AbstractFactoryExample.ServerFactory;
import com.mycompany.designpatterns.FactoryExample.Computer;
import com.mycompany.designpatterns.FlyweightExample.DrawingClient;

/**
 *
 * @author haunguyen
 */
public class Designpatterns {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        // Singleton
        System.out.println("v----------SINGLETON-----------------v");
        Singleton singleton = Singleton.getInstance();
        singleton.hi();
        System.out.println("^----------END SINGLETON-------------^");


        System.out.println("v----------Factory-----------------v");
        Factory.getContext();
        Computer myMachine = Factory.getComputer("server", "16gb", "1tb", "intel i5");
        System.out.println(myMachine);
        System.out.println("^----------END Factory-------------^");

        System.out.println("v----------Abstract Factory-----------------v");
        Computer pc = ComputerFactory.getComputer(new PcFactory("2 GB","500 GB","2.4 GHz"));
        System.out.println(pc);
        Computer machine = ComputerFactory.getComputer(new ServerFactory("16 GB","1 TB","2.9 GHz"));
        System.out.println(machine);
        System.out.println("^----------END AbstractFactory-------------^");

        System.out.println("v----------Flyweight-----------------v");
        DrawingClient drawing = new DrawingClient(500,600);
        System.out.println("^----------END Flyweight-------------^");

        System.out.println("v----------Abstract Factory-----------------v");
        FunitureFactoryAbstract factory = FunitureFactoryCreator.getFunitureFactory("plastic");

        Chair myChair = factory.createChair();
        Table myTable = factory.createTable();
        System.out.println("^----------END Abstract Factory-------------^");


    }
}
