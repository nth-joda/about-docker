package com.mycompany.designpatterns.AbstractFactoryExample;

import com.mycompany.designpatterns.FactoryExample.Computer;

/**
 * Abstract Factory thay thế ìf-else/ switch-case của Factory. Với mỗi sub-class có 1 factory trong đó có 1 hàm return SuperClass = new SubClass().
 */
public class ComputerFactory {
    public static Computer getComputer(ComputerAbstractFactoryInterface factory){
        return factory.createComputer();
    }
}
