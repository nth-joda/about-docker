/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.designpatterns;

/**
 *
 * @author haunguyen
 * - ensures that only one instance of the class exists
 */
public class Singleton {
    private static final Singleton mySingleton = new Singleton(); // static instance
    
    
    private Singleton(){
//        Private constructor for not allowing to create new instance
    }
    
    public static Singleton getInstance() {
        return mySingleton;
    }

    public void hi(){
        System.out.println("Singleton say hello");
    }
}
