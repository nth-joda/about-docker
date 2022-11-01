package com.mycompany.designpatterns.AbstractFactoryExample;

import com.mycompany.designpatterns.FactoryExample.Computer;
import com.mycompany.designpatterns.FactoryExample.Pc;

public class PcFactory implements ComputerAbstractFactoryInterface {
    private String ram;
    private String hdd;
    private String cpu;

    public PcFactory(String ram, String hdd, String cpu){
        this.ram=ram;
        this.hdd=hdd;
        this.cpu=cpu;
    }
    @Override
    public Computer createComputer() {
        return new Pc(ram, hdd, cpu);
    }
}
